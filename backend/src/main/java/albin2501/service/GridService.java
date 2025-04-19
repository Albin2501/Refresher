package albin2501.service;

import java.util.LinkedList;
import java.util.Queue;
import org.springframework.stereotype.Service;
import albin2501.dto.grid.GridDataDto;
import albin2501.dto.grid.GridDto;
import albin2501.dto.grid.GridSelectionDto;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.GridPersistence;
import albin2501.service.GridService;
import albin2501.util.Validator;

@Service
public class GridService {
    private GridPersistence gridPersistence;
    private Validator validator;

    public GridService(GridPersistence gridPersistence, Validator validator) {
        this.gridPersistence = gridPersistence;
        this.validator = validator;
    }

    public GridDataDto getGridData() {
        try {
            return gridPersistence.getGridData();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public GridDto getGrid(Long id) {
        try {
            validator.validateGridId(id, getGridData());
            return gridPersistence.getGrid(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public GridDto putGrid(GridSelectionDto gridSelectionDto) {
        try {
            validator.validateGridSelection(gridSelectionDto, getGridData());
            GridDto gridDto = getGrid(gridSelectionDto.getId()); // file exists, well formatted and can be written to

            // Algorithm in O(3 * n * m) at worst
            // Make first position default and let it represent upper-left
            if (gridSelectionDto.getPos1() == null && gridSelectionDto.getPos2() != null) {
                gridSelectionDto.setPos1(gridSelectionDto.getPos2());
                gridSelectionDto.setPos2(null);
            } else if (gridSelectionDto.getPos1() != null && gridSelectionDto.getPos2() != null) {
                // Point upper-left
                Long[] ul = {Math.min(gridSelectionDto.getPos1()[0],
                    gridSelectionDto.getPos2()[0]), Math.min(gridSelectionDto.getPos1()[1], gridSelectionDto.getPos2()[1])};
                // Point lower-right
                Long[] lr = {Math.max(gridSelectionDto.getPos1()[0],
                    gridSelectionDto.getPos2()[0]), Math.max(gridSelectionDto.getPos1()[1], gridSelectionDto.getPos2()[1])};

                gridSelectionDto.setPos1(ul);
                gridSelectionDto.setPos2(lr);
            }

            // Get highest number to differentiate, overflow at Long.MAX_VALUE
            Long max = 0L;

            for (int i = 0; i < gridDto.getGrid().length; i++) {
                for (int j = 0; j < gridDto.getGrid()[i].length; j++) {
                    if (max < gridDto.getGrid()[i][j])
                    max = gridDto.getGrid()[i][j];
                }
            }

            // Change grid with new input and enqueue all cells that are max
            Queue<int[]> queue = new LinkedList<>();
            max++;

            if (gridSelectionDto.getPos2() == null) {
                gridDto.getGrid()[gridSelectionDto.getPos1()[0].intValue()][gridSelectionDto.getPos1()[1].intValue()] = max;
                queue.add(new int[]{gridSelectionDto.getPos1()[0].intValue(), gridSelectionDto.getPos1()[1].intValue()});
            } else {
                for (int i = gridSelectionDto.getPos1()[0].intValue(); i <= gridSelectionDto.getPos2()[0].intValue(); i++) {
                    for (int j = gridSelectionDto.getPos1()[1].intValue(); j <= gridSelectionDto.getPos2()[1].intValue(); j++) {
                        gridDto.getGrid()[i][j] = max;
                        queue.add(new int[]{i,j});
                    }
                }
            }

            // Update grid via breath first search algorithm
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];
            
                int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            
                for (int[] d : directions) {
                    int ni = i + d[0];
                    int nj = j + d[1];
            
                    if (ni >= 0 && ni < gridDto.getGrid().length && nj >= 0 && nj < gridDto.getGrid()[i].length) {
                        Long nx = gridDto.getGrid()[ni][nj], curr = gridDto.getGrid()[i][j];
            
                        if (nx != 0 && nx < curr) {
                            gridDto.getGrid()[ni][nj] = curr;
                            queue.add(new int[]{ni, nj});
                        }
                    }
                }
            }

            return gridPersistence.putGrid(gridDto);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public GridDto clearGrid(Long id) {
        try {
            validator.validateGridId(id, getGridData());
            return gridPersistence.putGrid(new GridDto(id, gridPersistence.emptyArray()));
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

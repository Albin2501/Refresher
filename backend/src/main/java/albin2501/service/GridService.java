package albin2501.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import albin2501.dto.GridDataDto;
import albin2501.dto.GridDto;
import albin2501.dto.GridSelectionDto;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.GridPersistence;
import albin2501.service.GridService;
import albin2501.util.Validator;

@Service
public class GridService {

    public static GridDataDto getGridData() {
        try {
            return GridPersistence.getGridData();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static GridDto getGrid(Long id) {
        try {
            Validator.validateGridId(id, getGridData());
            return GridPersistence.getGrid(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static GridDto putGrid(GridSelectionDto gridSelectionDto) {
        try {
                Validator.validateGridSelection(gridSelectionDto, getGridData());
                GridDto gridDto = getGrid(gridSelectionDto.id); // file exists, well formatted and can be written to

            // Algorithm in O(3 * n * m) at worst
            // Make first position default and let it represent upper-left
            if (gridSelectionDto.pos1 == null && gridSelectionDto.pos2 != null) {
                gridSelectionDto.pos1 = gridSelectionDto.pos2;
                gridSelectionDto.pos2 = null;
            } else if (gridSelectionDto.pos1 != null && gridSelectionDto.pos2 != null) {
                // Point upper-left
                Long[] ul = {Math.min(gridSelectionDto.pos1[0], gridSelectionDto.pos2[0]), Math.min(gridSelectionDto.pos1[1], gridSelectionDto.pos2[1])};
                // Point lower-right
                Long[] lr = {Math.max(gridSelectionDto.pos1[0], gridSelectionDto.pos2[0]), Math.max(gridSelectionDto.pos1[1], gridSelectionDto.pos2[1])};

                gridSelectionDto.pos1 = ul;
                gridSelectionDto.pos2 = lr;
            }

            // Get highest number to differentiate, overflow at Long.MAX_VALUE
            Long max = 0L;

            for (int i = 0; i < gridDto.grid.length; i++) {
                for (int j = 0; j < gridDto.grid[i].length; j++) {
                    if (max < gridDto.grid[i][j])
                    max = gridDto.grid[i][j];
                }
            }

            // Change grid with new input and enqueue all cells that are max
            Queue<int[]> queue = new LinkedList<>();
            max++;

            if (gridSelectionDto.pos2 == null) {
                gridDto.grid[gridSelectionDto.pos1[0].intValue()][gridSelectionDto.pos1[1].intValue()] = max;
                queue.add(new int[]{gridSelectionDto.pos1[0].intValue(), gridSelectionDto.pos1[1].intValue()});
            } else {
                for (int i = gridSelectionDto.pos1[0].intValue(); i <= gridSelectionDto.pos2[0].intValue(); i++) {
                    for (int j = gridSelectionDto.pos1[1].intValue(); j <= gridSelectionDto.pos2[1].intValue(); j++) {
                        gridDto.grid[i][j] = max;
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
            
                    if (ni >= 0 && ni < gridDto.grid.length && nj >= 0 && nj < gridDto.grid[i].length) {
                        Long nx = gridDto.grid[ni][nj], curr = gridDto.grid[i][j];
            
                        if (nx != 0 && nx < curr) {
                            gridDto.grid[ni][nj] = curr;
                            queue.add(new int[]{ni, nj});
                        }
                    }
                }
            }

            return GridPersistence.putGrid(gridDto);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static GridDto clearGrid(Long id) {
        try {
            Validator.validateGridId(id, getGridData());
            return GridPersistence.putGrid(new GridDto(id, GridPersistence.emptyArray()));
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

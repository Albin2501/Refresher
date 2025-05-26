package albin2501.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Optional;
import org.springframework.stereotype.Service;
import albin2501.dto.grid.GridDataDto;
import albin2501.entity.Grid;
import albin2501.repository.GridRepository;
import albin2501.service.GridService;
import albin2501.util.DataGenerator;
import albin2501.util.Validator;

@Service
public class GridService {
    private GridRepository gridRepository;
    private Validator validator;

    public GridService(GridRepository gridRepository, Validator validator) {
        this.gridRepository = gridRepository;
        this.validator = validator;
    }

    public GridDataDto getGridData() {
        Optional<Long[]> idsOptional = gridRepository.findAllIds();
        validator.validateGrids(idsOptional);
        return new GridDataDto(DataGenerator.n, DataGenerator.m, idsOptional.get());
    }

    public Grid getGrid(Long id) {
        Optional<Grid> gridOptional = gridRepository.findById(id);
        validator.validateGrid(gridOptional);
        return gridOptional.get();
    }

    public Grid putGrid(Long id, Long[] pos1, Long[] pos2) {
        Grid grid = getGrid(id);
        GridDataDto gridDataDto = getGridData();
        validator.validateGridSelection(pos1, pos2, gridDataDto.n(), gridDataDto.m());

        // Algorithm in O(3 * n * m) at worst
        // Make first position default and let it represent upper-left
        if (pos1 == null && pos2 != null) {
            pos1 = pos2;
            pos2 = null;
        } else if (pos1 != null && pos2 != null) {
            // Point upper-left
            Long[] ul = {Math.min(pos1[0], pos2[0]),
                Math.min(pos1[1], pos2[1])};
            // Point lower-right
            Long[] lr = {Math.max(pos1[0], pos2[0]),
                Math.max(pos1[1], pos2[1])};

            pos1 = ul;
            pos2 = lr;
        }

        // Get highest number to differentiate, overflow at Long.MAX_VALUE
        Long max = 0L;

        for (int i = 0; i < DataGenerator.n; i++) {
            for (int j = 0; j < DataGenerator.m; j++) {
                Long curr = grid.getGridCellValue(i, j);
                max = max < curr ? curr : max;
            }
        }

        // Change grid with new input and enqueue all cells that are max
        Queue<int[]> queue = new LinkedList<>();
        max++;

        if (pos2 == null) {
            grid.setGridCellValue(pos1[0].intValue(), pos1[1].intValue(), max);
            queue.add(new int[]{pos1[0].intValue(), pos1[1].intValue()});
        } else {
            for (int i = pos1[0].intValue(); i <= pos2[0].intValue(); i++) {
                for (int j = pos1[1].intValue(); j <= pos2[1].intValue(); j++) {
                    grid.setGridCellValue(i, j, max);
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
        
                if (ni >= 0 && ni < DataGenerator.n && nj >= 0 && nj < DataGenerator.m) {
                    Long nx = grid.getGridCellValue(ni, nj);
                    Long curr = grid.getGridCellValue(i, j);;
        
                    if (nx != 0 && nx < curr) {
                        grid.setGridCellValue(ni, nj, curr);
                        queue.add(new int[]{ni, nj});
                    }
                }
            }
        }

        return gridRepository.save(grid);
    }

    public Grid clearGrid(Long id) {
        Grid grid = getGrid(id);
        Long[][] emptyArray = new Long[DataGenerator.n.intValue()][DataGenerator.m.intValue()];
        for (int i = 0; i < emptyArray.length; i++) {
            for (int j = 0; j < emptyArray[i].length; j++) {
                emptyArray[i][j] = 0L;
            }
        }

        grid.resetGridCells();
        return gridRepository.save(grid);
    }
}

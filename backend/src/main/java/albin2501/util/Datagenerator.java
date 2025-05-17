package albin2501.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import albin2501.entity.Grid;
import albin2501.repository.GridRepository;

@Component
public class DataGenerator implements CommandLineRunner {
    private GridRepository gridRepository;
    public static Long n = 25L; // # of rows
    public static Long m = 50L; // # of columns
    public static Long g = 3L; // # of grids
    
    public DataGenerator(GridRepository gridRepository) {
        this.gridRepository = gridRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (gridRepository.count() == 0) {
            Long[][] emptyArray = new Long[n.intValue()][m.intValue()];
            for (int i = 0; i < emptyArray.length; i++) {
                for (int j = 0; j < emptyArray[i].length; j++) {
                    emptyArray[i][j] = 0L;
                }
            }

            for (int i = 0; i < g; i++) {
                Grid grid = new Grid();
                grid.setGridCellsFromArray(emptyArray);
                gridRepository.save(grid);
            }
        }
    }
}

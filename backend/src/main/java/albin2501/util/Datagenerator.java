package albin2501.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import albin2501.entity.Grid;
import albin2501.repository.GridRepository;

@Component
public class Datagenerator implements CommandLineRunner {
    private GridRepository gridRepository;
    private int n = 25;
    private int m = 50;
    
    public Datagenerator(GridRepository gridRepository) {
        this.gridRepository = gridRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (gridRepository.count() == 0) {
            Long[][] emptyArray = new Long[n][m];
            for (int i = 0; i < emptyArray.length; i++) {
                for (int j = 0; j < emptyArray[i].length; j++) {
                    emptyArray[i][j] = 0L;
                }
            }

            for (int i = 0; i < 3; i++) {
                Grid grid = new Grid(null, emptyArray);
                gridRepository.save(grid);
            }
        }
    }
}

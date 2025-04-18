package albin2501.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import albin2501.dto.GridDataDto;
import albin2501.dto.GridDto;
import albin2501.exception.PersistenceException;

// TODO: Add database instead of .json file
// TODO: Refactor - dependency injection instead of static class methods

public class GridPersistence {
    private static Long n = 25L;
    private static Long m = 50L;
    private static int grids = 3;
    private static String fileName = "./src/main/java/albin2501/util/grid.json";

    // TODO: dto <-> entity

    public static GridDto[] getGrids() {
        GridDto[] data = null;

        try {
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();

            // Create new file if needed
            if (file.createNewFile()) {
                GridDto[] template = new GridDto[grids];
                Long[][] arr = emptyArray();
                for (int i = 0; i < template.length; i++) {
                    template[i] = new GridDto((long) i, arr);
                } 
                mapper.writeValue(file, template);
            }

            data = mapper.readValue(file, GridDto[].class);
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }

        return data;
    }

    public static GridDataDto getGridData() {
        return new GridDataDto(n, m, Arrays.stream(getGrids()).map(x -> x.id).toArray(Long[]::new));
    }

    public static GridDto getGrid(Long id) {
        return getGrids()[id.intValue()];
    }

    public static GridDto putGrid(GridDto gridDto) {
        // TODO: This isn't good - needs fix
        try {
            File file = new File(fileName);

            // Read file and replace grid via id
            ObjectMapper mapper = new ObjectMapper();
            GridDto[] data = mapper.readValue(file, GridDto[].class);
            data[gridDto.id.intValue()] = gridDto;
            mapper.writeValue(file, data);
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage(), e);
        }

        return gridDto;
    }

    public static Long[][] emptyArray() {
        Long[][] arr = new Long[n.intValue()][m.intValue()];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], 0L);
        }
        return arr;
    }
}

package albin2501.dto;

import java.util.Arrays;

public class GridDto {
    public Long id;
    public Long[][] grid;

    public GridDto() { }

    public GridDto(Long id, Long[][] grid) {
        this.id = id;
        this.grid = grid;
    }

    public String toString() {
        return new StringBuilder("id: ").append(id).append('\n').
        append("grid: ").append(Arrays.deepToString(grid)).toString();
    }
}

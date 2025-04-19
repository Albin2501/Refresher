package albin2501.dto.grid;

import java.util.Arrays;

public class GridDto {
    private Long id;
    private Long[][] grid;

    public GridDto() { }

    public GridDto(Long id, Long[][] grid) {
        this.id = id;
        this.grid = grid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[][] getGrid() {
        return this.grid;
    }

    public void setGrid(Long[][] grid) {
        this.grid = grid;
    }

    public String toString() {
        return new StringBuilder("id: ").append(id).append('\n').
        append("grid: ").append(Arrays.deepToString(grid)).toString();
    }
}

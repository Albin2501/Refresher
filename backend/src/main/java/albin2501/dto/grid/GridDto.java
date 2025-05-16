package albin2501.dto.grid;

import java.util.Objects;

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


    public GridDto id(Long id) {
        setId(id);
        return this;
    }

    public GridDto grid(Long[][] grid) {
        setGrid(grid);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GridDto)) {
            return false;
        }
        GridDto gridDto = (GridDto) o;
        return Objects.equals(id, gridDto.id) && Objects.equals(grid, gridDto.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grid);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", grid='" + getGrid() + "'" +
            "}";
    }
}

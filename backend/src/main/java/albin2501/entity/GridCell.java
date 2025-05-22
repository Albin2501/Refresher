package albin2501.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class GridCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grid grid;

    @Column(nullable = false)
    private int rowIndex;

    @Column(nullable = false)
    private int colIndex;

    @Column(nullable = false)
    private Long value;

    public GridCell() { }

    public GridCell(Long id, int rowIndex, int colIndex, Long value, Grid grid) {
        this.id = id;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
        this.grid = grid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public GridCell id(Long id) {
        setId(id);
        return this;
    }

    public GridCell rowIndex(int rowIndex) {
        setRowIndex(rowIndex);
        return this;
    }

    public GridCell colIndex(int colIndex) {
        setColIndex(colIndex);
        return this;
    }

    public GridCell value(Long value) {
        setValue(value);
        return this;
    }

    public GridCell grid(Grid grid) {
        setGrid(grid);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GridCell)) {
            return false;
        }
        GridCell gridCell = (GridCell) o;
        return Objects.equals(id, gridCell.id) && rowIndex == gridCell.rowIndex && colIndex == gridCell.colIndex && Objects.equals(value, gridCell.value) && Objects.equals(grid, gridCell.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowIndex, colIndex, value, grid);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rowIndex='" + getRowIndex() + "'" +
            ", colIndex='" + getColIndex() + "'" +
            ", value='" + getValue() + "'" +
            ", grid='" + getGrid() + "'" +
            "}";
    }
}

package albin2501.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import albin2501.util.DataGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grid_seq")
    @SequenceGenerator(name = "grid_seq", sequenceName = "grid_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "grid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GridCell> gridCells;
    
    public Long getGridCellValue(int i, int j) {
        return gridCells.stream().filter(x -> 
            x.getRowIndex() == i && x.getColIndex() == j
        ).findFirst().get().getValue();
    }

    public Long[][] getGridCellsAsArray() {
        Long[][] result = new Long[DataGenerator.n.intValue()][DataGenerator.m.intValue()];
        for (GridCell cell : gridCells) {
            result[cell.getRowIndex()][cell.getColIndex()] = cell.getValue();
        }
        
        return result;
    }

    public void setGridCellValue(int i, int j, Long value) {
        gridCells.stream().filter(x -> 
            x.getRowIndex() == i && x.getColIndex() == j
        ).findFirst().get().setValue(value);;
    }

    public void resetGridCells() {
        if (gridCells != null) {
            gridCells.stream().forEach(x -> 
                x.setValue(0L)
            );
        }
    }

    public void setGridCellsFromArray(Long[][] array) {
        if (gridCells == null) gridCells = new ArrayList<>();
        gridCells.clear();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                GridCell gridCell = new GridCell();
                gridCell.setGrid(this);
                gridCell.setRowIndex(i);
                gridCell.setColIndex(j);
                gridCell.setValue(array[i][j]);
                gridCells.add(gridCell);
            }
        }
    }

    public Grid() { }

    public Grid(Long id, List<GridCell> gridCells) {
        this.id = id;
        this.gridCells = gridCells;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GridCell> getGridCells() {
        return this.gridCells;
    }

    public void setGridCells(List<GridCell> gridCells) {
        this.gridCells = gridCells;
    }

    public Grid id(Long id) {
        setId(id);
        return this;
    }

    public Grid gridCells(List<GridCell> gridCells) {
        setGridCells(gridCells);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Grid)) {
            return false;
        }
        Grid grid = (Grid) o;
        return Objects.equals(id, grid.id) && Objects.equals(gridCells, grid.gridCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gridCells);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", gridCells='" + getGridCells() + "'" +
            "}";
    }
}

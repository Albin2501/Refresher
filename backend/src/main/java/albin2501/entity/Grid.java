package albin2501.entity;

import java.util.Objects;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String grid; // TODO: two-dimensional array not correctly mapped, workaround

    public Grid() { }

    public Grid(Long id, Long[][] grid) {
        this.id = id;
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.grid = mapper.writeValueAsString(grid);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize cells to JSON", e);
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[][] getGrid() {
        // TODO
        if (grid == null || grid.isBlank()) return new Long[0][0];

        try {
            // Use Jackson or another JSON parser (recommended)
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(grid, Long[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse cells JSON", e);
        }
    }

    public void setGrid(Long[][] grid) {
        // TODO
        if (grid == null) {
            this.grid = null;
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            this.grid = mapper.writeValueAsString(grid);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize cells to JSON", e);
        }
    }


    public Grid id(Long id) {
        setId(id);
        return this;
    }

    public Grid grid(Long[][] grid) {
        setGrid(grid);
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
        return Objects.equals(id, grid.id) && Objects.equals(grid, grid.grid);
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

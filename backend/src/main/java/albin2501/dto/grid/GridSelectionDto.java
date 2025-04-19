package albin2501.dto.grid;

import java.util.Arrays;

public class GridSelectionDto {
    private Long id;
    private Long[] pos1;
    private Long[] pos2;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getPos1() {
        return this.pos1;
    }

    public void setPos1(Long[] pos1) {
        this.pos1 = pos1;
    }

    public Long[] getPos2() {
        return this.pos2;
    }

    public void setPos2(Long[] pos2) {
        this.pos2 = pos2;
    }

    public String toString() {
        return new StringBuilder("id: ").append(id).append('\n').
        append("pos1: ").append(Arrays.toString(pos1)).append('\n').
        append("pos2: ").append(Arrays.toString(pos2)).toString();
    }
}

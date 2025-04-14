package albin2501.dto;

import java.util.Arrays;

public class GridSelectionDto {
    public Long id;
    public Long[] pos1;
    public Long[] pos2;

    public String toString() {
        return new StringBuilder("id: ").append(id).append('\n').
        append("pos1: ").append(Arrays.toString(pos1)).append('\n').
        append("pos2: ").append(Arrays.toString(pos2)).toString();
    }
}

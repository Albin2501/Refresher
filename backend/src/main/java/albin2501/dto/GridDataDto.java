package albin2501.dto;

import java.util.Arrays;

public class GridDataDto {
    public Long n;
    public Long m;
    public Long[] ids;

    public GridDataDto(Long n, Long m, Long[] ids) {
        this.n = n;
        this.m = m;
        this.ids = ids;
    }

    public String toString() {
        return new StringBuilder("n: ").append(n).append('\n').
        append("m: ").append(m).append('\n').append("ids: ").
        append(Arrays.toString(ids)).toString();
    }
}

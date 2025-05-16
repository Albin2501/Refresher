package albin2501.dto.grid;

import java.util.Objects;

public class GridDataDto {
    private Long n;
    private Long m;
    private Long[] ids;

    public GridDataDto() {
    }

    public GridDataDto(Long n, Long m, Long[] ids) {
        this.n = n;
        this.m = m;
        this.ids = ids;
    }

    public Long getN() {
        return this.n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public Long getM() {
        return this.m;
    }

    public void setM(Long m) {
        this.m = m;
    }

    public Long[] getIds() {
        return this.ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public GridDataDto n(Long n) {
        setN(n);
        return this;
    }

    public GridDataDto m(Long m) {
        setM(m);
        return this;
    }

    public GridDataDto ids(Long[] ids) {
        setIds(ids);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GridDataDto)) {
            return false;
        }
        GridDataDto gridDataDto = (GridDataDto) o;
        return Objects.equals(n, gridDataDto.n) && Objects.equals(m, gridDataDto.m) && Objects.equals(ids, gridDataDto.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, m, ids);
    }

    @Override
    public String toString() {
        return "{" +
            " n='" + getN() + "'" +
            ", m='" + getM() + "'" +
            ", ids='" + getIds() + "'" +
            "}";
    }    
}

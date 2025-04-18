package albin2501.util.datatype;

import java.util.Objects;

public class CustomEdge {
    private char start;
    private char end;
    private Long weight;

    public CustomEdge() { }

    public CustomEdge(char start, char end, Long weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return this.start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return this.end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public Long getWeight() {
        return this.weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomEdge)) {
            return false;
        }
        CustomEdge customEdge = (CustomEdge) o;
        return start == customEdge.start && end == customEdge.end;
    }

    public int hashCode() {
        return Objects.hash(start, end);
    }

    public String toString() {
        return (new StringBuilder("start: ").append(start).append("\n").
        append("end: ").append(end).append("\n").
        append("weight: ").append(weight)).toString();
    }
}

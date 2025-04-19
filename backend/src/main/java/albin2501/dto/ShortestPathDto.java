package albin2501.dto;

public class ShortestPathDto {
    private char start;
    private char end;

    public ShortestPathDto() { }

    public ShortestPathDto(char start, char end) {
        this.start = start;
        this.end = end;
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

    public String toString() {
        return (new StringBuilder("start: ").append(start).append("\n").
        append("end: ").append(end).append("\n")).toString();
    }
}

package albin2501.dto;

import albin2501.util.datatype.CustomNode;

public class ShortestPathDto {
    public String start;
    public String end;
    public CustomNode node;
    // TODO: add different algorithm names and their time
    // some meta data, like what was the fastest, ...

    public ShortestPathDto() { }

    public ShortestPathDto(String start, String end, CustomNode node) {
        this.start = start;
        this.end = end;
        this.node = node;
    }

    public String toString() {
        return (new StringBuilder("start: ").append(start).append("\n").
        append("end: ").append(end).append("\n").
        append("node: ").append(node.toString())).toString();
    }
    
}

package albin2501.dto;

import albin2501.util.datatype.CustomEdge;

public class ShortestPathResultDto {
    private CustomEdge[] edges;
    // TODO: add different algorithm names and their time
    // some meta data, like what was the fastest, ...

    public ShortestPathResultDto() { }

    public ShortestPathResultDto(CustomEdge[] edges) {
        this.edges = edges;
    }

    public CustomEdge[] getEdges() {
        return this.edges;
    }

    public void setEdges(CustomEdge[] edges) {
        this.edges = edges;
    }

    public String toString() {
        return (new StringBuilder("edges: ").append(edges)).toString();
    }
}

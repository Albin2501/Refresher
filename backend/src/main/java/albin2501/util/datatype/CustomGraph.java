package albin2501.util.datatype;

import java.util.stream.Stream;

public class CustomGraph {
    private char[] nodes;
    private CustomEdge[] edges;

    public CustomGraph() { }

    public CustomGraph(char[] nodes, CustomEdge[] edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public char[] getNodes() {
        return this.nodes;
    }

    public void setNodes(char[] nodes) {
        this.nodes = nodes;
    }

    public CustomEdge[] getEdges() {
        return this.edges;
    }

    public void setEdges(CustomEdge[] edges) {
        this.edges = edges;
    }

    public String toString() {
        return (new StringBuilder("nodes: ").append(Stream.of(nodes).map(x -> x.toString())).append("\n").
        append("edges: ").append(Stream.of(edges).map(x -> x.toString()))).toString();
    }
}

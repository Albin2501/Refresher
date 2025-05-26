package albin2501.datatype;

import java.util.ArrayList;

public class CustomGraph {
    private String[] nodes;
    private ArrayList<CustomEdge> edges;

    public CustomGraph() { }

    public CustomGraph(String[] nodes, ArrayList<CustomEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public String[] getNodes() {
        return this.nodes;
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }

    public ArrayList<CustomEdge> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<CustomEdge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "{" +
            " nodes='" + getNodes() + "'" +
            ", edges='" + getEdges() + "'" +
            "}";
    }
}

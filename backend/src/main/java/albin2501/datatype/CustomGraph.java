package albin2501.datatype;

import java.util.ArrayList;
import java.util.Objects;

public class CustomGraph {
    private char[] nodes;
    private ArrayList<CustomEdge> edges;

    public CustomGraph() { }

    public CustomGraph(char[] nodes, ArrayList<CustomEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public char[] getNodes() {
        return this.nodes;
    }

    public void setNodes(char[] nodes) {
        this.nodes = nodes;
    }

    public ArrayList<CustomEdge> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<CustomEdge> edges) {
        this.edges = edges;
    }

    public CustomGraph nodes(char[] nodes) {
        setNodes(nodes);
        return this;
    }

    public CustomGraph edges(ArrayList<CustomEdge> edges) {
        setEdges(edges);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomGraph)) {
            return false;
        }
        CustomGraph customGraph = (CustomGraph) o;
        return Objects.equals(nodes, customGraph.nodes) && Objects.equals(edges, customGraph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }

    @Override
    public String toString() {
        return "{" +
            " nodes='" + new String(getNodes()) + "'" +
            ", edges='" + getEdges() + "'" +
            "}";
    }
}

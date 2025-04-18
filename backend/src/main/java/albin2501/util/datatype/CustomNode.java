package albin2501.util.datatype;

import java.util.ArrayList;
import java.util.Objects;

public class CustomNode {
    String name;
    ArrayList<CustomNodeContainer> edges;


    public CustomNode() { }

    public CustomNode(String name, ArrayList<CustomNodeContainer> edges) {
        this.name = name;
        this.edges = edges;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CustomNodeContainer> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<CustomNodeContainer> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomNode)) {
            return false;
        }
        CustomNode customNode = (CustomNode) o;
        return Objects.equals(name, customNode.name) && Objects.equals(edges, customNode.edges);
    }

    @Override
    public String toString() {
        return (new StringBuilder("name: ").append(name).append("\n").
        append("edges: ").append(edges.toString())).toString();
    }
}

package albin2501.datatype;

public class CustomEdge {
    private String startNode;
    private String endNode;
    private Long weight;

    public CustomEdge() { }

    public CustomEdge(String startNode, String endNode, Long weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public String getStartNode() {
        return this.startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return this.endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    public Long getWeight() {
        return this.weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
            " startNode='" + getStartNode() + "'" +
            ", endNode='" + getEndNode() + "'" +
            ", weight='" + getWeight() + "'" +
            "}";
    }
}

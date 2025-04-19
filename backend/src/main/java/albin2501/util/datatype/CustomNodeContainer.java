package albin2501.util.datatype;

import java.util.Objects;

public class CustomNodeContainer {
    private Long value;
    private CustomNode customNode;
    
    public CustomNodeContainer() { }

    public CustomNodeContainer(Long value, CustomNode customNode) {
        this.value = value;
        this.customNode = customNode;
    }

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public CustomNode getCustomNode() {
        return this.customNode;
    }

    public void setCustomNode(CustomNode customNode) {
        this.customNode = customNode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomNodeContainer)) {
            return false;
        }
        CustomNodeContainer customNodeContainer = (CustomNodeContainer) o;
        return Objects.equals(value, customNodeContainer.value) && Objects.equals(customNode, customNodeContainer.customNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, customNode);
    }

    @Override
    public String toString() {
        return (new StringBuilder("value: ").append(value).append("\n").
        append("customNode: ").append(customNode.toString())).toString();
    }
}

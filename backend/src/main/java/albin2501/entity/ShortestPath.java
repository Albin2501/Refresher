package albin2501.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

import org.hibernate.annotations.Check;

@Entity
public class ShortestPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Check(constraints = "method1Time >= 0")
    @Column(nullable = false)
    private Long method1Time;

    @Check(constraints = "method2Time >= 0")
    @Column(nullable = false)
    private Long method2Time;
    
    @Check(constraints = "method3Time >= 0")
    @Column(nullable = false)
    private Long method3Time;

    @Check(constraints = "method4Time >= 0")
    @Column(nullable = false)
    private Long method4Time;

    @Column(nullable = false)
    private char startNode;

    @Column(nullable = false)
    private char endNode;

    @Check(constraints = "nodes_amount >= 0")
    @Column(nullable = false)
    private float nodesAmount;

    public ShortestPath() { }

    public ShortestPath(Long id, Long method1Time, Long method2Time, Long method3Time, Long method4Time, char startNode, char endNode, float nodesAmount) {
        this.id = id;
        this.method1Time = method1Time;
        this.method2Time = method2Time;
        this.method3Time = method3Time;
        this.method4Time = method4Time;
        this.startNode = startNode;
        this.endNode = endNode;
        this.nodesAmount = nodesAmount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMethod1Time() {
        return this.method1Time;
    }

    public void setMethod1Time(Long method1Time) {
        this.method1Time = method1Time;
    }

    public Long getMethod2Time() {
        return this.method2Time;
    }

    public void setMethod2Time(Long method2Time) {
        this.method2Time = method2Time;
    }

    public Long getMethod3Time() {
        return this.method3Time;
    }

    public void setMethod3Time(Long method3Time) {
        this.method3Time = method3Time;
    }

    public Long getMethod4Time() {
        return this.method4Time;
    }

    public void setMethod4Time(Long method4Time) {
        this.method4Time = method4Time;
    }

    public char getStartNode() {
        return this.startNode;
    }

    public void setStartNode(char startNode) {
        this.startNode = startNode;
    }

    public char getEndNode() {
        return this.endNode;
    }

    public void setEndNode(char endNode) {
        this.endNode = endNode;
    }

    public float getNodesAmount() {
        return this.nodesAmount;
    }

    public void setNodesAmount(float nodesAmount) {
        this.nodesAmount = nodesAmount;
    }

    public ShortestPath id(Long id) {
        setId(id);
        return this;
    }

    public ShortestPath method1Time(Long method1Time) {
        setMethod1Time(method1Time);
        return this;
    }

    public ShortestPath method2Time(Long method2Time) {
        setMethod2Time(method2Time);
        return this;
    }

    public ShortestPath method3Time(Long method3Time) {
        setMethod3Time(method3Time);
        return this;
    }

    public ShortestPath method4Time(Long method4Time) {
        setMethod4Time(method4Time);
        return this;
    }

    public ShortestPath startNode(char startNode) {
        setStartNode(startNode);
        return this;
    }

    public ShortestPath endNode(char endNode) {
        setEndNode(endNode);
        return this;
    }

    public ShortestPath nodesAmount(float nodesAmount) {
        setNodesAmount(nodesAmount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ShortestPath)) {
            return false;
        }
        ShortestPath shortestPath = (ShortestPath) o;
        return Objects.equals(id, shortestPath.id) && Objects.equals(method1Time, shortestPath.method1Time) && Objects.equals(method2Time, shortestPath.method2Time) && Objects.equals(method3Time, shortestPath.method3Time) && Objects.equals(method4Time, shortestPath.method4Time) && startNode == shortestPath.startNode && endNode == shortestPath.endNode && nodesAmount == shortestPath.nodesAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method1Time, method2Time, method3Time, method4Time, startNode, endNode, nodesAmount);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", method1Time='" + getMethod1Time() + "'" +
            ", method2Time='" + getMethod2Time() + "'" +
            ", method3Time='" + getMethod3Time() + "'" +
            ", method4Time='" + getMethod4Time() + "'" +
            ", startNode='" + getStartNode() + "'" +
            ", endNode='" + getEndNode() + "'" +
            ", nodesAmount='" + getNodesAmount() + "'" +
            "}";
    }    
}

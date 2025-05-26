package albin2501.datatype;

public class ShortestPathData {
    private Long functionAmount;
    private char startNodeMax;
    private char endNodeMax;
    private float nodesAmountAverage;
    private Long method1Average;
    private Long method2Average;
    private Long method3Average;
    private Long method4Average;

    public ShortestPathData() { }

    public ShortestPathData(Long functionAmount, char startNodeMax, char endNodeMax, float nodesAmountAverage, Long method1Average, Long method2Average, Long method3Average, Long method4Average) {
        this.functionAmount = functionAmount;
        this.startNodeMax = startNodeMax;
        this.endNodeMax = endNodeMax;
        this.nodesAmountAverage = nodesAmountAverage;
        this.method1Average = method1Average;
        this.method2Average = method2Average;
        this.method3Average = method3Average;
        this.method4Average = method4Average;
    }

    public Long getFunctionAmount() {
        return this.functionAmount;
    }

    public void setFunctionAmount(Long functionAmount) {
        this.functionAmount = functionAmount;
    }

    public char getStartNodeMax() {
        return this.startNodeMax;
    }

    public void setStartNodeMax(char startNodeMax) {
        this.startNodeMax = startNodeMax;
    }

    public char getEndNodeMax() {
        return this.endNodeMax;
    }

    public void setEndNodeMax(char endNodeMax) {
        this.endNodeMax = endNodeMax;
    }

    public float getNodesAmountAverage() {
        return this.nodesAmountAverage;
    }

    public void setNodesAmountAverage(float nodesAmountAverage) {
        this.nodesAmountAverage = nodesAmountAverage;
    }

    public Long getMethod1Average() {
        return this.method1Average;
    }

    public void setMethod1Average(Long method1Average) {
        this.method1Average = method1Average;
    }

    public Long getMethod2Average() {
        return this.method2Average;
    }

    public void setMethod2Average(Long method2Average) {
        this.method2Average = method2Average;
    }

    public Long getMethod3Average() {
        return this.method3Average;
    }

    public void setMethod3Average(Long method3Average) {
        this.method3Average = method3Average;
    }

    public Long getMethod4Average() {
        return this.method4Average;
    }

    public void setMethod4Average(Long method4Average) {
        this.method4Average = method4Average;
    }

    @Override
    public String toString() {
        return "{" +
            " functionAmount='" + getFunctionAmount() + "'" +
            ", startNodeMax='" + getStartNodeMax() + "'" +
            ", endNodeMax='" + getEndNodeMax() + "'" +
            ", nodesAmountAverage='" + getNodesAmountAverage() + "'" +
            ", method1Average='" + getMethod1Average() + "'" +
            ", method2Average='" + getMethod2Average() + "'" +
            ", method3Average='" + getMethod3Average() + "'" +
            ", method4Average='" + getMethod4Average() + "'" +
            "}";
    }
}

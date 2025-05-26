package albin2501.datatype;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortestPathData {
    private Long functionAmount;
    private char startNodeMax;
    private char endNodeMax;
    private float nodesAmountAverage;
    private Long method1Average;
    private Long method2Average;
    private Long method3Average;
    private Long method4Average;
}

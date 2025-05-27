package albin2501.datatype;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortestPathSummary {
    private Long methodTime;
    private CustomEdge[] shortestPath;

    public int getSize() {
        return this.shortestPath.length;
    }
}

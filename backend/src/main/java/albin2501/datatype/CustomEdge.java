package albin2501.datatype;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomEdge {
    private String startNode;
    private String endNode;
    private Long weight;
}

package albin2501.datatype;

import java.util.ArrayList;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomGraph {
    private String[] nodes;
    private ArrayList<CustomEdge> edges;
}

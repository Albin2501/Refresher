package albin2501.validator;

import org.springframework.stereotype.Component;

import albin2501.datatype.CustomGraph;
import albin2501.exception.ValidationException;

@Component
public class ShortestPathValidator {
    
    public void validateShortestPathDto(CustomGraph graph, String startNode, String endNode) {
        if (graph == null) throw new ValidationException("Graph hasn't been generated yet.");

        boolean start = false;
        boolean end = false;
        for (String name : graph.getNodes()) {
            start = start || name.equals(startNode);
            end = end || name.equals(endNode);
        }

        if (!start) throw new ValidationException("Starting node does not exist.");
        if (!end) throw new ValidationException("Ending node does not exist.");
    }
}

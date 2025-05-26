package albin2501.validator;

import java.util.Optional;
import org.springframework.stereotype.Component;
import albin2501.entity.Grid;
import albin2501.exception.NotFoundException;
import albin2501.exception.ValidationException;

@Component
public class GridValidator {
    
    public void validateGrid(Optional<Grid> gridOptional) {
        if (!gridOptional.isPresent()) throw new NotFoundException("Grid not found.");
    }

    public void validateGrids(Optional<Long[]> idsOptional) {
        if (!idsOptional.isPresent()) throw new NotFoundException("No grids exist.");
    }

    public void validateGridSelection(Long[] pos1, Long[] pos2, Long n, Long m) {
        StringBuilder message = new StringBuilder("");

        // Sanitize user input
        if ((pos1 == null && pos1 == null) ||
            (pos1 != null && pos1.length != 2) ||
            (pos2 != null && pos2.length != 2) ||
            (pos1 != null && (pos1[0] == null || pos1[1] == null)) ||
            (pos2 != null && pos2[0] == null || pos2[1] == null))
        throw new ValidationException("Selection not well formatted.");

        if (pos1 != null &&
            ((pos1[0] < 0 || pos1[0] >= n) ||
            (pos1[1] < 0 || pos1[1] >= m)))
        message.append("First selected point out of bounds.");

        if (pos2 != null &&
            ((pos2[0] < 0 || pos2[0] >= n) ||
            (pos2[1] < 0 || pos2[1] >= m)))
        message.append(message.length() > 0 ? " " : "").append("Second selected point out of bounds.");

        if (message.length() > 0)
        throw new ValidationException(message.toString());
    }
}

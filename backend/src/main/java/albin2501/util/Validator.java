package albin2501.util;

import albin2501.dto.GridDataDto;
import albin2501.dto.GridSelectionDto;
import albin2501.exception.NotFoundException;
import albin2501.exception.ValidationException;

public class Validator {

    public static void validateGridId(Long id, GridDataDto gridDataDto) {
        for (Long x : gridDataDto.ids) {
            if (id == x) return;
        }
        throw new NotFoundException("Grid not found.");
    }

    public static void validateGridSelection(GridSelectionDto gridSelectionDto, GridDataDto gridDataDto) {
        validateGridId(gridSelectionDto.id, gridDataDto);
        StringBuilder message = new StringBuilder("");

        // Sanitize user input
        if ((gridSelectionDto.pos1 == null && gridSelectionDto.pos1 == null) ||
            (gridSelectionDto.pos1 != null && gridSelectionDto.pos1.length != 2) ||
            (gridSelectionDto.pos2 != null && gridSelectionDto.pos2.length != 2) ||
            (gridSelectionDto.pos1 != null && (gridSelectionDto.pos1[0] == null || gridSelectionDto.pos1[1] == null)) ||
            (gridSelectionDto.pos2 != null && (gridSelectionDto.pos2[0] == null || gridSelectionDto.pos2[1] == null)))
        throw new ValidationException("Selection not well formatted.");

        if (gridSelectionDto.pos1 != null &&
            ((gridSelectionDto.pos1[0] < 0 || gridSelectionDto.pos1[0] >= gridDataDto.n) ||
            (gridSelectionDto.pos1[1] < 0 || gridSelectionDto.pos1[1] >= gridDataDto.m)))
        message.append("First selected point out of bounds.");

        if (gridSelectionDto.pos2 != null &&
            ((gridSelectionDto.pos2[0] < 0 || gridSelectionDto.pos2[0] >= gridDataDto.n) ||
            (gridSelectionDto.pos2[1] < 0 || gridSelectionDto.pos2[1] >= gridDataDto.m)))
        message.append(message.length() > 0 ? " " : "").append("Second selected point out of bounds.");

        if (message.length() > 0)
        throw new ValidationException(message.toString());
    }
}

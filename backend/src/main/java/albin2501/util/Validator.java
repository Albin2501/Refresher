package albin2501.util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import albin2501.dto.GridDataDto;
import albin2501.dto.GridSelectionDto;
import albin2501.dto.ImageDto;
import albin2501.entity.TextArt;
import albin2501.exception.NotFoundException;
import albin2501.exception.ValidationException;
import albin2501.util.datatype.CustomGraph;

import java.io.IOException;

// TODO: Refactor - dependency injection instead of static class methods

public class Validator {

    public Validator() { }

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

    public static void validateTextArtId(Long id, TextArt[] textArt) {
        for (TextArt art : textArt) {
            if (art.id == id) return;
        }
        throw new NotFoundException("TextArt not found.");
    }

    public static void validateTextArt(ImageDto imageDto) {
        StringBuilder message = new StringBuilder("");

        if (imageDto.image == null || imageDto.width == null || imageDto.height == null)
        throw new ValidationException("Image not well formatted.");

        if (imageDto.width < 1)
        message.append("Width must be at least 1 character wide.");

        if (imageDto.width > 256)
        message.append(message.length() > 0 ? " " : "").append("Width must be at most 256 characters wide.");

        if (imageDto.height < 1)
        message.append("Height must be at least 1 character wide.");

        // since chars are approximately twice as tall as they are wide, the height needs to be halved
        // so that the image is square if 1:1=width:height
        if (imageDto.height > 128)
        message.append(message.length() > 0 ? " " : "").append("Height must be at most 128 characters wide.");

        if (imageDto.image.isEmpty())
        message.append(message.length() > 0 ? " " : "").append("File is empty.");

        if (imageDto.image.getOriginalFilename() == null)
        message.append(message.length() > 0 ? " " : "").append("File name must be set.");

        try {
            BufferedImage image = ImageIO.read(imageDto.image.getInputStream());
            if (image == null)
            message.append(message.length() > 0 ? " " : "").append("Image is not valid.");
        } catch (IOException e) {
            message.append(message.length() > 0 ? " " : "").append("Image can't be read.");
        }

        if (message.length() > 0)
        throw new ValidationException(message.toString());
    }

    public void validateGraph(CustomGraph customGraph) {
        if (customGraph == null)
        throw new ValidationException("Graph needs to be generated first.");
    }
}

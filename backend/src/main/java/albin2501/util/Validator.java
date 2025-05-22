package albin2501.util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import albin2501.datatype.CustomGraph;
import albin2501.dto.shortestPath.ShortestPathDto;
import albin2501.entity.Grid;
import albin2501.entity.TextArt;
import albin2501.exception.NotFoundException;
import albin2501.exception.ValidationException;
import java.io.IOException;
import java.util.Optional;

@Component
public class Validator {

    public Validator() { }

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

    public void validateTextArtId(Long id, TextArt[] textArt) {
        for (TextArt art : textArt) {
            if (art.getId() == id) return;
        }
        throw new NotFoundException("TextArt not found.");
    }

    public void validateTextArt(MultipartFile image, Long width, Long height) {
        StringBuilder message = new StringBuilder("");

        if (image == null || width == null || height == null)
        throw new ValidationException("Image not well formatted.");

        if (width < 1)
        message.append("Width must be at least 1 character wide.");

        if (width > 256)
        message.append(message.length() > 0 ? " " : "").append("Width must be at most 256 characters wide.");

        if (height < 1)
        message.append("Height must be at least 1 character wide.");

        // since chars are approximately twice as tall as they are wide, the height needs to be halved
        // so that the image is square if 1:1=width:height
        if (height > 128)
        message.append(message.length() > 0 ? " " : "").append("Height must be at most 128 characters wide.");

        if (image.isEmpty())
        message.append(message.length() > 0 ? " " : "").append("File is empty.");

        if (image.getOriginalFilename() == null)
        message.append(message.length() > 0 ? " " : "").append("File name must be set.");

        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            if (bufferedImage == null)
            message.append(message.length() > 0 ? " " : "").append("Image is not valid.");
        } catch (IOException e) {
            message.append(message.length() > 0 ? " " : "").append("Image can't be read.");
        }

        if (message.length() > 0)
        throw new ValidationException(message.toString());
    }

    public void validateShortestPathDto(CustomGraph graph, ShortestPathDto shortestPathDto) {
        if (graph == null) throw new ValidationException("Graph hasn't been generated yet.");
        if (shortestPathDto == null) throw new ValidationException("A path needs to be selected.");

        boolean start = false;
        boolean end = false;
        for (char name : graph.getNodes()) {
            start = start || name == shortestPathDto.startNode();
            end = end || name == shortestPathDto.endNode();
        }

        if (!start) throw new ValidationException("Starting node does not exist.");
        if (!end) throw new ValidationException("Ending node does not exist.");
    }
}

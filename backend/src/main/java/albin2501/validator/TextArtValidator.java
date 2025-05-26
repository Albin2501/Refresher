package albin2501.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import albin2501.entity.TextArt;
import albin2501.exception.NotFoundException;
import albin2501.exception.ValidationException;

@Component
public class TextArtValidator {
    
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
}

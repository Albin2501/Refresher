package albin2501.service;

import java.util.Arrays;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import albin2501.dto.textArt.ImageDto;
import albin2501.dto.textArt.TextArtDto;
import albin2501.entity.TextArt;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.TextArtPersistence;
import albin2501.util.Mapper;
import albin2501.util.Validator;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class TextArtService {
    private TextArtPersistence textArtPersistence;
    private Validator validator;
    private Mapper mapper;

    public TextArtService(TextArtPersistence textArtPersistence, Validator validator, Mapper mapper) {
        this.textArtPersistence = textArtPersistence;
        this.validator = validator;
        this.mapper = mapper;
    }

    public TextArtDto[] getTextArt() {
        try {
            return Arrays.stream(textArtPersistence.getTextArt()).
                    map(x -> mapper.textArtToTextArtDto(x)).toArray(TextArtDto[]::new);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public TextArt[] getTextArtValidate() {
        try {
            return textArtPersistence.getTextArt();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("null")
    public TextArtDto postTextArt(ImageDto imageDto) {
        try {
            validator.validateTextArt(imageDto);

            // Scale image to user specification
            // Write scaled image to BufferedImage in gray scale
            char[] asciiChars = {
                ' ', '.', ':', '-', '~', '+', '*', '|', 'n', 'a', 'A', 'N', '0', '&', '#', '@'
            };
            BufferedImage image = ImageIO.read(imageDto.image().getInputStream());
            BufferedImage resizedImage = new BufferedImage(imageDto.width().intValue(),
                imageDto.height().intValue(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(image.getScaledInstance(imageDto.width().intValue(),
                imageDto.height().intValue(), Image.SCALE_DEFAULT), 0, 0, null);
            g2d.dispose();

            // Create ASCII art of resizedImage
            int argb, grayVal, index;
            float normalizedVal, correctVal;

            StringBuilder asciiArt = new StringBuilder();
            for (int y = 0; y < imageDto.height().intValue(); y++) {
                for (int x = 0; x < imageDto.width().intValue(); x++) {
                    // .getRGB gets 4 bytes where red, green and blue are all uniform because of TYPE_BYTE_GRAY
                    // binary conjunction gets (in this case) the blue chanel for gray value of pixel
                    // normalized value of gray scale between 0 and 1
                    // gamma correction via y = x^1/γ with 1.5 making the picture darker
                    // resulting index gets round to also get 0 or 255 in the respective ASCII scale
                    argb = resizedImage.getRGB(x, y);
                    grayVal = argb & 0xff;
                    normalizedVal = grayVal / 255f;
                    correctVal = (float) Math.pow(normalizedVal, 1.5);
                    index = Math.round(correctVal * (asciiChars.length - 1));
                    asciiArt.append(asciiChars[index]);
                }
                asciiArt.append("\n");
            }

            String imageName = imageDto.image().getOriginalFilename();

            return mapper.textArtToTextArtDto(
                textArtPersistence.postTextArt(asciiArt.toString(),
                imageName.length() < 32 ? imageName : 
                (imageName.substring(0, 32) + "..."),
                imageDto.width(), imageDto.height())
            );
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean deleteTextArt() {
        try {
            return textArtPersistence.deleteTextArt();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean deleteTextArtById(Long id) {
        try {
            validator.validateTextArtId(id, getTextArtValidate());
            return textArtPersistence.deleteTextArtById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

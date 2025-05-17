package albin2501.service;

import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import albin2501.entity.TextArt;
import albin2501.exception.ServiceException;
import albin2501.repository.TextArtRepository;
import albin2501.util.Validator;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TextArtService {
    private TextArtRepository textArtRepository;
    private Validator validator;

    public TextArtService(TextArtRepository textArtRepository, Validator validator) {
        this.textArtRepository = textArtRepository;
        this.validator = validator;
    }

    public TextArt[] getTextArt() {
        List<TextArt> textArts = textArtRepository.findAllCustom();
        return textArts.toArray(new TextArt[textArts.size()]);
    }

    public TextArt postTextArt(MultipartFile image, Long width, Long height) {
        try {
            validator.validateTextArt(image, width, height);

            // Scale image to user specification
            // Write scaled image to BufferedImage in gray scale
            char[] asciiChars = {
                ' ', '.', ':', '-', '~', '+', '*', '|', 'n', 'a', 'A', 'N', '0', '&', '#', '@'
            };
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            BufferedImage resizedImage = new BufferedImage(width.intValue(), height.intValue(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(bufferedImage.getScaledInstance(width.intValue(),
                height.intValue(), Image.SCALE_DEFAULT), 0, 0, null);
            g2d.dispose();

            // Create ASCII art of resizedImage
            int argb, grayVal, index;
            float normalizedVal, correctVal;

            StringBuilder asciiArt = new StringBuilder();
            for (int y = 0; y < height.intValue(); y++) {
                for (int x = 0; x < width.intValue(); x++) {
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

            String imageName = image.getOriginalFilename();
            TextArt textArt = new TextArt(null, asciiArt.toString(),
                imageName.length() < 32 ? imageName : (imageName.substring(0, 29) + "..."),
                LocalDateTime.now(), width, height);

            return textArtRepository.save(textArt);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean deleteTextArt() {
        textArtRepository.deleteAll();
        return textArtRepository.count() == 0;
    }

    public boolean deleteTextArtById(Long id) {
        if (!textArtRepository.existsById(id)) return false;
        textArtRepository.deleteById(id);
        return true;
    }
}

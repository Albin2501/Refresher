package albin2501.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
    public MultipartFile image;
    public Long width;
    public Long height;

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Long getWidth() {
        return this.width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setHeight(Long height) {
        // since chars are twice as tall as they are wide, the height needs to be halved
        // so that the image is square if 1:1 = width:height
        this.height = height / 2;
    }

    public String toString() {
        return new StringBuilder("image: ").append(image.getOriginalFilename()).
        append('\n').append("width: ").append(width).
        append('\n').append("height: ").append(height).toString();
    }
}

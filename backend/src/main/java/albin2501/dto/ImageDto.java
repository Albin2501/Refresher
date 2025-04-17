package albin2501.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
    public MultipartFile image;
    public Long width;
    public Long height;

    public ImageDto() { }

    public ImageDto(MultipartFile image, Long width, Long height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return new StringBuilder("image: ").append(image.getOriginalFilename()).
        append('\n').append("width: ").append(width).
        append('\n').append("height: ").append(height).toString();
    }
}

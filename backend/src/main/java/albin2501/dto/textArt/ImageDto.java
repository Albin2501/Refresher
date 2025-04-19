package albin2501.dto.textArt;

import org.springframework.web.multipart.MultipartFile;

public record ImageDto(MultipartFile image, Long width, Long height) { }

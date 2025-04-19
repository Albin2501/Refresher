package albin2501.dto.textArt;

import java.time.LocalDateTime;

public record TextArtDto(String art, Long id, String name, LocalDateTime time, Long width, Long height) { }

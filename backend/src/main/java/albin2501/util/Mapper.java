package albin2501.util;

import albin2501.dto.TextArtDto;
import albin2501.entity.TextArt;

public class Mapper {

    public static TextArt textArtDtoToTextArt(TextArtDto textArtDto) {
        return new TextArt(textArtDto.art, null, null, null, textArtDto.width, textArtDto.height);
    }

    public static TextArtDto textArtToTextArtDto(TextArt textArt) {
        return new TextArtDto(textArt.art, textArt.id, textArt.name, textArt.time, textArt.width, textArt.height);
    }
}

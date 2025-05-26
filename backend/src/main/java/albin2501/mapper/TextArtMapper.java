package albin2501.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import albin2501.dto.textArt.TextArtDto;
import albin2501.entity.TextArt;

@Mapper
public interface TextArtMapper {
    
    TextArtMapper INSTANCE = Mappers.getMapper(TextArtMapper.class);

    TextArtDto textArtToTextArtDto(TextArt textArt);
}

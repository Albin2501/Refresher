package albin2501.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import albin2501.datatype.ShortestPathData;
import albin2501.dto.shortestPath.ShortestPathDataDto;

@Mapper
public interface ShortestPathMapper {
    
    ShortestPathMapper INSTANCE = Mappers.getMapper(ShortestPathMapper.class);
    
    ShortestPathDataDto shortestPathDataToShortestPathDataDto(ShortestPathData shortestPathData);
}

package albin2501.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import albin2501.dto.grid.GridDto;
import albin2501.entity.Grid;

@Mapper
public interface GridMapper {
 
    GridMapper INSTANCE = Mappers.getMapper(GridMapper.class);
 
    @Mapping(target = "grid", expression = "java(grid.getGridCellsAsArray())")
    GridDto gridToGridDto(Grid grid);
}

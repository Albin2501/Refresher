package albin2501.util;

import org.springframework.stereotype.Component;
import albin2501.dto.grid.GridDto;
import albin2501.dto.shortestPath.ShortestPathDataDto;
import albin2501.dto.textArt.TextArtDto;
import albin2501.entity.Grid;
import albin2501.entity.ShortestPathData;
import albin2501.entity.TextArt;

@Component
public class Mapper {

    public GridDto gridToGridDto(Grid grid) {
        return new GridDto(grid.getId(), grid.getGrid());
    }

    public TextArt textArtDtoToTextArt(TextArtDto textArtDto) {
        return new TextArt(textArtDto.art(), null, null, null, textArtDto.width(), textArtDto.height());
    }

    public TextArtDto textArtToTextArtDto(TextArt textArt) {
        return new TextArtDto(textArt.getArt(), textArt.getId(), textArt.getName(),
            textArt.getTime(), textArt.getWidth(), textArt.getHeight());
    }

    public ShortestPathDataDto shortestPathDataToShortestPathDataDto(ShortestPathData shortestPathData) {
        return null;
    }
}

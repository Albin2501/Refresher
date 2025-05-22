package albin2501.util;

import org.springframework.stereotype.Component;
import albin2501.datatype.ShortestPathData;
import albin2501.dto.grid.GridDto;
import albin2501.dto.shortestPath.ShortestPathDataDto;
import albin2501.dto.textArt.TextArtDto;
import albin2501.entity.Grid;
import albin2501.entity.TextArt;

@Component
public class Mapper {

    public GridDto gridToGridDto(Grid grid) {
        return new GridDto(grid.getId(), grid.getGridCellsAsArray());
    }

    public TextArtDto textArtToTextArtDto(TextArt textArt) {
        return new TextArtDto(textArt.getArt(), textArt.getId(), textArt.getName(),
            textArt.getTime(), textArt.getWidth(), textArt.getHeight());
    }

    public ShortestPathDataDto shortestPathDataToShortestPathDataDto(ShortestPathData shortestPathData) {
        return new ShortestPathDataDto(shortestPathData.getFunctionAmount(), shortestPathData.getStartNodeMax(),
        shortestPathData.getEndNodeMax(), shortestPathData.getNodesAmountAverage(), shortestPathData.getMethod1Average(),
        shortestPathData.getMethod2Average(), shortestPathData.getMethod3Average(), shortestPathData.getMethod4Average());
    }
}

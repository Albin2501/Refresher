package albin2501.endpoint;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import albin2501.datatype.CustomEdge;
import albin2501.datatype.CustomGraph;
import albin2501.dto.shortestPath.ShortestPathDataDto;
import albin2501.dto.shortestPath.ShortestPathDto;
import albin2501.exception.ServiceException;
import albin2501.exception.ValidationException;
import albin2501.service.ShortestPathService;
import albin2501.util.Config;
import albin2501.util.Mapper;

@RestController
@RequestMapping(path = ShortestPathEndpoint.url)
@CrossOrigin(origins = Config.frontendBase) // CORS access control
public class ShortestPathEndpoint {
    final static String url = "/shortestPath";
    private final ShortestPathService shortestPathService;
    private final Mapper mapper;

    public ShortestPathEndpoint(ShortestPathService shortestPathService, Mapper mapper) {
        this.shortestPathService = shortestPathService;
        this.mapper = mapper;
    }

    // A graph gets randomly generated in the backend and displayed in the frontend.
    // The shortest path between a given start- and endpoint will be calculated 
    // using different algorithms in an asynchronous fashion.
    // Color the shortest path in the graph and display meta data about the calculations.
    // Meta data about the calculations are also persisted and can be retrieved.

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomGraph getRandomGraph() {
        return shortestPathService.getRandomGraph();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomEdge[] getShortestPath(@RequestBody ShortestPathDto shortestPathDto) {
        try {
            return shortestPathService.getShortestPath(shortestPathDto);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/data")
    @ResponseStatus(HttpStatus.OK)
    public ShortestPathDataDto getShortestPathData() {
        return mapper.shortestPathDataToShortestPathDataDto(shortestPathService.getShortestPathData());
    }
}

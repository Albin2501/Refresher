package albin2501.endpoint;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import albin2501.datatype.*;
import albin2501.dto.shortestPath.*;
import albin2501.exception.*;
import albin2501.mapper.ShortestPathMapper;
import albin2501.service.ShortestPathService;
import albin2501.Application;

@RestController
@RequestMapping(path = "/shortestPath")
@CrossOrigin(origins = Application.frontendBase)
public class ShortestPathEndpoint {
    
    @Autowired
    private ShortestPathService shortestPathService;

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
    public CustomEdge[] getShortestPath(@RequestBody ShortestPathSelectionDto shortestPathSelectionDto) {
        try {
            return shortestPathService.getShortestPath(
                shortestPathSelectionDto.startNode(),
                shortestPathSelectionDto.endNode()
                );
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/data")
    @ResponseStatus(HttpStatus.OK)
    public ShortestPathDataDto getShortestPathData() {
        return ShortestPathMapper.INSTANCE.shortestPathDataToShortestPathDataDto(shortestPathService.getShortestPathData());
    }
}

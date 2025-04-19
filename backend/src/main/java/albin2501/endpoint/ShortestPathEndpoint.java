package albin2501.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import albin2501.dto.ShortestPathDataDto;
import albin2501.dto.ShortestPathDto;
import albin2501.exception.ServiceException;
import albin2501.service.ShortestPathService;
import albin2501.util.Config;
import albin2501.util.Mapper;
import albin2501.util.datatype.CustomNode;

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
    // Use a SQL database (via JDBC) to persist average meta data about the calculations.

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomNode getRandomGraph() {
        try {
            return shortestPathService.getRandomGraph();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortestPathDto getShortestPath(@RequestBody CustomNode graph) {
        try {
            return shortestPathService.getShortestPath();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/data")
    @ResponseStatus(HttpStatus.OK)
    public ShortestPathDataDto getShortestPathData() {
        try {
            return mapper.shortestPathDataToShortestPathDataDto(shortestPathService.getShortestPathData());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
package albin2501.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import albin2501.dto.ShortestPathDto;
import albin2501.exception.ServiceException;
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortestPathDto getShortestPath(@RequestBody Object object) {
        try {
            return mapper.shortestPathToShortestPathDto(shortestPathService.getShortestPath());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
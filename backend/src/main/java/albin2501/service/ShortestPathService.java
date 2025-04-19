package albin2501.service;

import org.springframework.stereotype.Service;

import albin2501.dto.ShortestPathDto;
import albin2501.dto.ShortestPathResultDto;
import albin2501.entity.ShortestPathData;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.ShortestPathPersistence;
import albin2501.util.Validator;
import albin2501.util.datatype.CustomGraph;

@Service
public class ShortestPathService {
    private final ShortestPathPersistence shortestPathPersistence;
    private final Validator validator;

    public ShortestPathService(ShortestPathPersistence shortestPathPersistence, Validator validator) {
        this.shortestPathPersistence = shortestPathPersistence;
        this.validator = validator;
    }

    public CustomGraph getRandomGraph() {
        // TODO


        this.shortestPathPersistence.setCurrGraph(null);
        return this.shortestPathPersistence.getCurrGraph();
    }

    public ShortestPathResultDto getShortestPath(ShortestPathDto shortestPathDto) {
        validator.validateGraph(this.shortestPathPersistence.getCurrGraph());

        // TODO

        return null;
    }

    public ShortestPathData getShortestPathData() {
        // TODO

        try {
            return shortestPathPersistence.getShortestPathData();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

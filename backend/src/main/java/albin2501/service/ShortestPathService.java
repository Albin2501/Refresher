package albin2501.service;

import org.springframework.stereotype.Service;

import albin2501.entity.ShortestPath;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.ShortestPathPersistence;

@Service
public class ShortestPathService {
    private final ShortestPathPersistence shortestPathPersistence;

    public ShortestPathService(ShortestPathPersistence shortestPathPersistence) {
        this.shortestPathPersistence = shortestPathPersistence;
    }

    public ShortestPath getShortestPath() {
        // A graph gets created in a visual tool in the frontend (but persisted
        // in another method in the backend).
        // Take a graph and calculate the shortest path between given start- and 
        // endpoint. Use different algorithms to achieve this in an asynchronous fashion
        // and measure the time to compare. Color the shortest path in the graph.
        // No history log. 1 graph at a time. Use a SQL database (JDBC).
        // Use your own data types. Offer method to generate a random graph (~25 nodes).

        try {
            return shortestPathPersistence.getShortestPath();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

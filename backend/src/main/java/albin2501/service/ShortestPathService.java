package albin2501.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import albin2501.dto.ShortestPathDto;
import albin2501.entity.ShortestPathData;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.ShortestPathPersistence;
import albin2501.util.datatype.CustomNode;
import albin2501.util.datatype.CustomNodeContainer;

@Service
public class ShortestPathService {
    private final ShortestPathPersistence shortestPathPersistence;

    public ShortestPathService(ShortestPathPersistence shortestPathPersistence) {
        this.shortestPathPersistence = shortestPathPersistence;
    }

    public CustomNode getRandomGraph() {
        CustomNode graph = null;
        int numOfNodes = (int) (Math.random() * 26); // [0, 25]
        int neighbors; // [0, max(numOfNodes, 2)] = [0, 2]
        char name; // [A, Z]

        for (int i = 0; i < numOfNodes; i++) {
            ArrayList<CustomNodeContainer> edges = new ArrayList<>();
            CustomNodeContainer edge;
            Long edgeWeight; // [-100, 100]

            name = (char) (65 + i);
            neighbors = Math.max((int) (Math.random() * numOfNodes), 2);

            for (int j = 0; j < neighbors; j++) {
                edgeWeight = Math.round(Math.random() * 101) * (Math.random() < 0.5 ? -1 : 1);
                edge = new CustomNodeContainer(edgeWeight, null);
                edges.add(edge);
            }

            graph = new CustomNode(name, edges);
        }

        return graph;
    }

    public ShortestPathDto getShortestPath() {

        return null;
    }

    public ShortestPathData getShortestPathData() {
        try {
            return shortestPathPersistence.getShortestPathData();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

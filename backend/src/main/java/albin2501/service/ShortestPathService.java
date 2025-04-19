package albin2501.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import albin2501.dto.shortestPath.ShortestPathDto;
import albin2501.dto.shortestPath.ShortestPathResultDto;
import albin2501.entity.ShortestPathData;
import albin2501.exception.PersistenceException;
import albin2501.exception.ServiceException;
import albin2501.persistence.ShortestPathPersistence;
import albin2501.util.Validator;
import albin2501.util.datatype.CustomEdge;
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
        // directed and cyclic graph with non-negative weights
        int numNodes = 2 + (int) (Math.random() * 24); // [2, 25]
        char[] nodes = new char[numNodes];
        ArrayList<CustomEdge> edges = new ArrayList<>();

        // Firstly, initialize what nodes exist 
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = (char) (65 + i);
        }

        char start, end;
        Long weight; // [0, 99]
        CustomEdge edge;

        // Secondly, create random edges to form a directed graph
        for (int i = 0; i < nodes.length; i++) {
            start = nodes[i];
            // It gets less likely, the more edges already exist
            for (int j = 0; Math.random() * j < 0.5201; j++) {
                end = nodes[(int) (Math.random() * (i + 1))];
                if (start == end) break;
                weight = Math.round(Math.random() * 100);
                edge = new CustomEdge(start, end, weight);
                if (!edges.contains(edge)) edges.add(edge);
            }
        }

        this.shortestPathPersistence.setCurrGraph(new CustomGraph(nodes, edges));
        return this.shortestPathPersistence.getCurrGraph();
    }

    public ShortestPathResultDto getShortestPath(ShortestPathDto shortestPathDto) {
        // TODO
        validator.validateGraph(this.shortestPathPersistence.getCurrGraph());

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

package albin2501.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import albin2501.datatype.*;
import albin2501.exception.ServiceException;
import albin2501.repository.ShortestPathDataRepository;
import albin2501.validator.ShortestPathValidator;

@Service
public class ShortestPathService {

    @Autowired
    private ShortestPathDataRepository shortestPathDataRepository;
    @Autowired
    private ShortestPathValidator shortestPathValidator;
    private CustomGraph currGraph; // volatile memory

    public CustomGraph getRandomGraph() {
        // directed and cyclic graph with non-negative weights
        int numNodes = 2 + (int) (Math.random() * 24); // [2, 25]
        String[] nodes = new String[numNodes];
        ArrayList<CustomEdge> edges = new ArrayList<>();

        // Firstly, initialize what nodes exist 
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = (char) (65 + i) + "";
        }

        String start, end;
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

        this.currGraph = new CustomGraph(nodes, edges);
        return this.currGraph;
    }

    public CustomEdge[] getShortestPath(String startNode, String endNode) {
        try {
            CustomGraph currGraph = this.currGraph;
            shortestPathValidator.validateShortestPathDto(currGraph, startNode, endNode);

            Object[] results = new Object[4];
            Thread[] threads = new Thread[results.length];
            Runnable[] methods = new Runnable[] {
                () -> method1(currGraph, startNode, endNode, results[0]),
                () -> method2(currGraph, startNode, endNode, results[1]),
                () -> method3(currGraph, startNode, endNode, results[2]),
                () -> method4(currGraph, startNode, endNode, results[3])
            };

            // Async
            for (int i = 0; i < methods.length; i++) {
                threads[i] = new Thread(methods[i]);
                threads[i].start();
            }

            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
            // Sync

            // TODO
            // 1. Check if all values are the same, if not Exception
            // 2. Construct return value
            // 3. Save values in database shortestPathDataRepository

            return null;
        } catch (InterruptedException e) {
            throw new ServiceException("Threads could not be merged.");
        }
    }

    public ShortestPathData getShortestPathData() {
        // return shortestPathDataRepository.getData();
        return null;
    }

    // Bellman-Ford algorithm O(V * E)
    private void method1(CustomGraph graph, String startNode, String endNode, Object object) {
        // TODO: Testing
        Long startTime = System.nanoTime();

        String[] nodes = graph.getNodes();
        HashMap<String, Long> distances = new HashMap<>(nodes.length);
        HashMap<String, String> pred = new HashMap<>(nodes.length);
        ArrayList<CustomEdge> edges = graph.getEdges();
        CustomEdge edge;
        String start, end;
        Long weight;

        for (int i = 0; i < nodes.length; i++) {
            if (startNode == nodes[i]) distances.put(nodes[i], 0L);
            else distances.put(nodes[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                edge = edges.get(j);
                start = edge.getStartNode();
                end = edge.getEndNode();
                weight = edge.getWeight();

                if (distances.get(start) != Long.MAX_VALUE && 
                    distances.get(start) + weight < distances.get(end)) {
                    distances.put(end, distances.get(start) + weight);
                    pred.put(end, start);
                }
            }
        }

        ArrayList<CustomEdge> shortestPath = new ArrayList<>();
        List<String> path = new ArrayList<>();
        String current = endNode;

        while (current != startNode) {
            path.add(current);
            current = pred.get(current);
            if (!pred.containsKey(current)) {
                path.clear();
                break;
            }
        }

        path.add(startNode);
        Collections.reverse(path);

        Long endTime = System.nanoTime();
        Long time = endTime - startTime;

        object = null;
    }

    // Dijkstra's algorithm with list O(V^2)
    private void method2(CustomGraph graph, String startNode, String endNode, Object object) {
        object = null;
    }

    // Dijkstra's algorithm with binary heap th binary heap O((E + V) * log V)
    private void method3(CustomGraph graph, String startNode, String endNode, Object object) {
        object = null;
    }

    // Dijkstra's algorithm with Fibonacci heap O(E + V * log V)
    private void method4(CustomGraph graph, String startNode, String endNode, Object object) {
        object = null;
    }
}

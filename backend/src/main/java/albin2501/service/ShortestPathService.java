package albin2501.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import albin2501.datatype.CustomEdge;
import albin2501.datatype.CustomGraph;
import albin2501.datatype.ShortestPathData;
import albin2501.dto.shortestPath.ShortestPathDto;
import albin2501.exception.ServiceException;
import albin2501.repository.ShortestPathDataRepository;
import albin2501.util.Validator;

@Service
public class ShortestPathService {
    private CustomGraph currGraph; 
    private final ShortestPathDataRepository shortestPathDataRepository;
    private final Validator validator;

    public ShortestPathService(ShortestPathDataRepository shortestPathDataRepository, Validator validator) {
        this.shortestPathDataRepository = shortestPathDataRepository;
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

        this.currGraph = new CustomGraph(nodes, edges);
        return this.currGraph;
    }

    public CustomEdge[] getShortestPath(ShortestPathDto shortestPathDto) {
        try {
            // TODO: Basic implementation done

            CustomGraph currGraph = this.currGraph;
            validator.validateShortestPathDto(currGraph, shortestPathDto);

            Object[] results = new Object[4];
            Thread[] threads = new Thread[results.length];
            Runnable[] methods = new Runnable[] {
                () -> method1(currGraph, shortestPathDto, results[0]),
                () -> method2(currGraph, shortestPathDto, results[1]),
                () -> method3(currGraph, shortestPathDto, results[2]),
                () -> method4(currGraph, shortestPathDto, results[3])
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
    private void method1(CustomGraph graph, ShortestPathDto shortestPathDto, Object object) {
        /*
        TODO: Basic implementation done, untested

        Long startTime = System.nanoTime();

        char[] nodes = graph.getNodes();
        HashMap<Character, Long> distances = new HashMap<>(nodes.length);
        HashMap<Character, Character> pred = new HashMap<>(nodes.length);
        ArrayList<CustomEdge> edges = graph.getEdges();
        CustomEdge edge;
        char start, end;
        Long weight;

        for (int i = 0; i < nodes.length; i++) {
            if (shortestPathDto.start() == nodes[i]) distances.put(nodes[i], 0L);
            else distances.put(nodes[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                edge = edges.get(j);
                start = edge.getStart();
                end = edge.getEnd();
                weight = edge.getWeight();

                if (distances.get(start) != Long.MAX_VALUE && 
                    distances.get(start) + weight < distances.get(end)) {
                    distances.put(end, distances.get(start) + weight);
                    pred.put(end, start);
                }
            }
        }

        ArrayList<CustomEdge> shortestPath = new ArrayList<>();
        List<Character> path = new ArrayList<>();
        char current = shortestPathDto.end();

        while (current != shortestPathDto.start()) {
            path.add(current);
            current = pred.get(current);
            if (!pred.containsKey(current)) {
                path.clear();
                break;
            }
        }

        path.add(shortestPathDto.start());
        Collections.reverse(path);

        Long endTime = System.nanoTime();
        Long time = endTime - startTime;

        object = null;
        */
    }

    // Dijkstra's algorithm with list O(V^2)
    private void method2(CustomGraph graph, ShortestPathDto shortestPathDto, Object object) {
        object = null;
    }

    // Dijkstra's algorithm with binary heap th binary heap O((E + V) * log V)
    private void method3(CustomGraph graph, ShortestPathDto shortestPathDto, Object object) {
        object = null;
    }

    // Dijkstra's algorithm with Fibonacci heap O(E + V * log V)
    private void method4(CustomGraph graph, ShortestPathDto shortestPathDto, Object object) {
        object = null;
    }
}

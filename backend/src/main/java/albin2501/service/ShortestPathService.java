package albin2501.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import albin2501.datatype.*;
import albin2501.entity.ShortestPath;
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

            // Run 4 different methods to calculate the shortest path
            ShortestPathSummary[] res = new ShortestPathSummary[4];
            Thread[] threads = new Thread[res.length];
            Runnable[] methods = new Runnable[] {
                () -> method1(currGraph, startNode, endNode, res[0]),
                () -> method2(currGraph, startNode, endNode, res[1]),
                () -> method3(currGraph, startNode, endNode, res[2]),
                () -> method4(currGraph, startNode, endNode, res[3])
            };
            for (int i = 0; i < methods.length; i++) {
                threads[i] = new Thread(methods[i]);
                threads[i].start();
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }

            // Check if all values are the same
            if (!(res[0] != null && res[1] != null && res[2] != null && res[3] != null &&
                res[0].getSize() == res[1].getSize() &&
                res[1].getSize() == res[2].getSize() &&
                res[2].getSize() == res[3].getSize()
            )) throw new ServiceException("Different shortest path calculated.");
            
            ShortestPath shortestPath = ShortestPath.builder().
            method1Time(res[0].getMethodTime()).method2Time(res[1].getMethodTime()).
            method3Time(res[2].getMethodTime()).method4Time(res[3].getMethodTime()).
            startNode(startNode).endNode(endNode).nodesAmount(res[0].getSize()).build();

            // Save data for statistics
            shortestPathDataRepository.save(shortestPath);

            // Return result of the method2 (Dijkstra's algorithm with list)
            return res[1].getShortestPath();
        } catch (InterruptedException e) {
            throw new ServiceException("Threads could not be merged.");
        }
    }

    public ShortestPathData getShortestPathData() {
        // return shortestPathDataRepository.getData();
        return null;
    }

    // Bellman-Ford algorithm O(V * E)
    private void method1(CustomGraph graph, String startNode, String endNode, ShortestPathSummary shortestPathSummary) {
        ShortestPathSummary res = new ShortestPathSummary();
        Long startTime = System.nanoTime();

        // TODO
        

        Long endTime = System.nanoTime();
        res.setMethodTime(endTime - startTime);

        shortestPathSummary = res;
    }

    // Dijkstra's algorithm with list O(V^2)
    private void method2(CustomGraph graph, String startNode, String endNode, ShortestPathSummary shortestPathSummary) {
        ShortestPathSummary res = new ShortestPathSummary();
        Long startTime = System.nanoTime();

        // TODO
        

        Long endTime = System.nanoTime();
        res.setMethodTime(endTime - startTime);

        shortestPathSummary = res;
    }

    // Dijkstra's algorithm with binary heap th binary heap O((E + V) * log V)
    private void method3(CustomGraph graph, String startNode, String endNode, ShortestPathSummary shortestPathSummary) {
        ShortestPathSummary res = new ShortestPathSummary();
        Long startTime = System.nanoTime();

        // TODO
        

        Long endTime = System.nanoTime();
        res.setMethodTime(endTime - startTime);

        shortestPathSummary = res;
    }

    // Dijkstra's algorithm with Fibonacci heap O(E + V * log V)
    private void method4(CustomGraph graph, String startNode, String endNode, ShortestPathSummary shortestPathSummary) {
        ShortestPathSummary res = new ShortestPathSummary();
        Long startTime = System.nanoTime();

        // TODO
        

        Long endTime = System.nanoTime();
        res.setMethodTime(endTime - startTime);

        shortestPathSummary = res;
    }
}

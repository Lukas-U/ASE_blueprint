package com.tuwien.ASE_blueprint.service;

import com.tuwien.ASE_blueprint.model.dijkstra.Edge;
import com.tuwien.ASE_blueprint.model.dijkstra.Graph;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService {

    public List<Integer> getShortestPath(Graph graph, int source, int target) {

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        // Initialize distances and predecessors
        graph.getAdjacencyList().keySet().forEach(v -> {
            distances.put(v, Integer.MAX_VALUE);
            predecessors.put(v, null);
        });
        distances.put(source, 0);

        priorityQueue.add(new Edge(source, 0));

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll().getTarget();

            if (currentVertex == target) {
                break; // Stop once the target is reached
            }

            if (!visited.add(currentVertex)) {
                continue;
            }

            for (Edge edge : graph.getEdges(currentVertex)) {
                if (visited.contains(edge.getTarget())) {
                    continue;
                }

                int newDist = distances.get(currentVertex) + edge.getWeight();
                if (newDist < distances.get(edge.getTarget())) {
                    distances.put(edge.getTarget(), newDist);
                    predecessors.put(edge.getTarget(), currentVertex);
                    priorityQueue.add(new Edge(edge.getTarget(), newDist));
                }
            }
        }

        return reconstructPath(predecessors, source, target);
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> predecessors, int source, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer step = target;

        if (predecessors.get(step) == null) {
            return path;
        }
        path.add(step);
        while ((step = predecessors.get(step)) != null) {
            path.addFirst(step);
        }
        return path;
    }

}

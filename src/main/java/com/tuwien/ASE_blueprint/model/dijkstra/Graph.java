package com.tuwien.ASE_blueprint.model.dijkstra;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Graph {

    private Map<Integer, List<Edge>> adjacencyList = new HashMap<>();

    public List<Edge> getEdges(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

}

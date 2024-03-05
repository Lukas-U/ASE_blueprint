package com.tuwien.ASE_blueprint.controller;

import com.tuwien.ASE_blueprint.model.Graph;
import com.tuwien.ASE_blueprint.model.GraphType;
import com.tuwien.ASE_blueprint.repository.GraphRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GraphController {

    @Autowired
    private GraphRepository graphRepository;

    @GetMapping("/graphs/")
    public List<Graph> getAllGraphs() {
        List<Graph> allGraphsinDB = graphRepository.findAll();
        System.out.println("Nr. of Graphs in DB: " + allGraphsinDB.size());

        return allGraphsinDB;
    }

    @GetMapping("/graphs/{id}")
    public ResponseEntity<Graph> getGraphById(@PathVariable(value = "id") Long graphId) throws ResourceNotFoundException {
        Graph graph = graphRepository.findById(graphId).orElseThrow(() ->
                new ResourceNotFoundException("Graph with id '" + graphId + "' not found!"));
        return ResponseEntity.ok().body(graph);
    }

    @PostMapping("/graphs/create/")
    public Graph createGraph(@Valid @RequestParam Graph graph) {
        return graphRepository.save(graph);
    }

    @GetMapping("/graphs/generate/")
    public Graph generateGraph() {
        Graph graph = new Graph();
        graph.setNrOfVertices(4);
        graph.setGraphType(GraphType.TREE);
        System.out.println("Created Graph '" + graph + "'.");
        return graphRepository.save(graph);
    }
}

package com.tuwien.ASE_blueprint;

import com.tuwien.ASE_blueprint.model.Graph;
import com.tuwien.ASE_blueprint.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@SpringBootApplication
@RestController
public class AseBlueprintApplication {

	@Autowired
	private GraphRepository graphRepository;

	public static void main(String[] args) {
		SpringApplication.run(AseBlueprintApplication.class, args);
	}

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return String.format("Hello %s!", name);
	}

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
		System.out.println("Created Graph '" + graph + "'.");
		return graphRepository.save(graph);
	}

}

package com.tuwien.ASE_blueprint.controller;

import com.tuwien.ASE_blueprint.model.dijkstra.DijkstraModel;
import com.tuwien.ASE_blueprint.service.DijkstraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DijkstraController {
    private static final String BASE_URL = "/api/dijkstra";
    private final DijkstraService dijkstraService;

    public DijkstraController(DijkstraService dijkstraService) {
        this.dijkstraService = dijkstraService;
    }

    @PostMapping(BASE_URL)
    public List<Integer> calculateShortestPath(@RequestBody DijkstraModel dijkstraModel) {
        return dijkstraService.getShortestPath(dijkstraModel.getGraph(), dijkstraModel.getSource(), dijkstraModel.getTarget());
    }

}

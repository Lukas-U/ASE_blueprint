package com.tuwien.ASE_blueprint.controller;

import com.tuwien.ASE_blueprint.model.planetsAndPortals.Ass1;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.Ass2;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.Ass3;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.Ass4;
import com.tuwien.ASE_blueprint.service.PlanetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class PlanetController {

    private static final String BASE_URL = "/api/planet/";

    private final PlanetService planetService;

    @PostMapping(BASE_URL + "1")
    public ResponseEntity<?> generateSolution1(@RequestBody Ass1 assignment1) {
        log.info("POST request to {}", BASE_URL + "1");
        return ResponseEntity.ok(planetService.generateSolution1(assignment1.getGalaxy(), assignment1.getStartPlanetId()));
    }

    @PostMapping(BASE_URL + "2")
    public ResponseEntity<?> generateSolution2(@RequestBody Ass2 assignment2) {
        log.info("POST request to {}", BASE_URL + "2");
        return ResponseEntity.ok(planetService.generateSolution2(assignment2.getGalaxy()));
    }

    @PostMapping(BASE_URL + "3")
    public ResponseEntity<?> generateSolution3(@RequestBody Ass3 assignment3) {
        log.info("POST request to {}", BASE_URL + "3");
        return ResponseEntity.ok(planetService.generateSolution3(assignment3.getGalaxy(), assignment3.getSource(), assignment3.getTarget()));
    }

    @PostMapping(BASE_URL + "4")
    public ResponseEntity<?> generateSolution4(@RequestBody Ass4 assignment4) {
        log.info("POST request to {}", BASE_URL + "4");
        return ResponseEntity.ok(planetService.generateSolution4(assignment4.getGalaxy()));
    }
}

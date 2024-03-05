package com.tuwien.ASE_blueprint.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuwien.ASE_blueprint.model.hyperloop.Ass1;
import com.tuwien.ASE_blueprint.model.hyperloop.Ass2;
import com.tuwien.ASE_blueprint.model.hyperloop.Ass3;
import com.tuwien.ASE_blueprint.model.hyperloop.Ass4;
import com.tuwien.ASE_blueprint.service.HyperloopService;
import com.tuwien.ASE_blueprint.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Slf4j
public class HyperloopController {

    private HyperloopService hyperloopService;

    private static final String BASE_URL = "/api/hyperloop/";
    public static final String POST_USING_BODY = "POST {} using body {}";

    public HyperloopController(HyperloopService hyperloopService) {
        this.hyperloopService = hyperloopService;
    }

    @PostMapping(BASE_URL + "1")
    public List<Point> generateSolution1(@RequestBody Ass1 assignment1) {
        log.info(POST_USING_BODY, BASE_URL + "1", assignment1.toString());
        return hyperloopService.generateSolution1(assignment1);
    }

    @PostMapping(BASE_URL + "2")
    public List<Point> generateSolution2(@RequestBody Ass2 assignment2) {
        log.info(POST_USING_BODY, BASE_URL + "2", assignment2.toString());
        return hyperloopService.generateSolution2(assignment2);
    }

    @PostMapping(BASE_URL + "3")
    public List<Point> generateSolution3(@RequestBody Ass3 assignment3) {
        log.info(POST_USING_BODY, BASE_URL + "3", assignment3.toString());
        return hyperloopService.generateSolution3(assignment3);
    }

    @PostMapping(BASE_URL + "4")
    public List<Point> generateSolution4(@RequestBody Ass4 assignment4) {
        log.info(POST_USING_BODY, BASE_URL + "4", assignment4.toString());
        return hyperloopService.generateSolution4(assignment4);
    }
    @GetMapping("/hyperloop/create")
    public void createCoordinateSystem() throws JsonProcessingException {
        hyperloopService.fetchInputJson();
    }
}

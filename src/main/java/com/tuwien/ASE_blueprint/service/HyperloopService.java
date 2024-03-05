package com.tuwien.ASE_blueprint.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuwien.ASE_blueprint.model.Obstacle;
import com.tuwien.ASE_blueprint.model.hyperloop.*;
import com.tuwien.ASE_blueprint.model.Point;
import com.tuwien.ASE_blueprint.repository.CoordinateSystemRepository;
import com.tuwien.ASE_blueprint.repository.PointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HyperloopService {

    private final CoordinateSystemRepository coordinateSystemRepository;
    private final PointRepository pointRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private static String requestInputJsonAsString = """
            {
                "obstacle": {
                    "pointB": {"x": -3, "y": -5},
                    "pointA”: ｛”x": 4，"y": -6｝｝
                "targets": [
                    {"x": -1, "y": -4},
                    {"x": -5, "y": -9},
                    {"x": 6，"y: -6}，
                    {"x": 6，"y": 8｝
                ]
            }
            """;

    public HyperloopService(CoordinateSystemRepository coordinateSystemRepository, PointRepository pointRepository) {
        this.coordinateSystemRepository = coordinateSystemRepository;
        this.pointRepository = pointRepository;
    }

    public List<Point> generateSolution1(Assignment1 assignment1) {
        List<Point> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        assignment1.getPoints().forEach((Point point) -> {
            // basically just check whether the line prevents the point from reaching the 0/0 origin
            // if the line is above 0 and the point is below it, it can reach 0
            if (assignment1.getLine().getY() > 0 && point.getY() < assignment1.getLine().getY()) {
                result.add(point);
                stringBuilder.append(point.getX()).append(" ").append(point.getY()).append(" ");
            }
            // if the line is below 0 and the point is above it, it can reach 0
            if (assignment1.getLine().getY() < 0 && point.getY() > assignment1.getLine().getY()) {
                result.add(point);
                stringBuilder.append(point.getX()).append(" ").append(point.getY()).append(" ");
            }
        });

        log.info(stringBuilder.toString());
        return result;
    }

    // TODO this does not incorporate Solution 1
    public List<Point> generateSolution2(Assignment2 assignment2) {
        List<Point> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        // counterclockwise angle, measured in radian, between the positive X-axis, and the point (x, y)
        // this means we can use it to model a line from X to the point
        double angle1 = Math.atan2(assignment2.getStartPoint().getX(), assignment2.getStartPoint().getY());
        double angle2 = Math.atan2(assignment2.getEndPoint().getX(), assignment2.getEndPoint().getY());

        double highAngle;
        double lowAngle;
        if (angle1 < angle2) {
            lowAngle = angle1;
            highAngle = angle2;
        } else {
            lowAngle = angle2;
            highAngle = angle1;
        }

        log.info("Angles: {} {}", highAngle, lowAngle);

        // for each of the "normal" points, check wether its angle is smaller than the lower one
        // or bigger than the higher one
        assignment2.getPoints().forEach((Point point) -> {
            double angle = Math.atan2(point.getX(), point.getY());
            //log.info("Point: {} Angle: {}", point.toString(), angle);

            if (angle < lowAngle || angle > highAngle) {
                result.add(point);
                stringBuilder.append(point.getX()).append(" ").append(point.getY()).append(" ");
            }

        });

        log.info(stringBuilder.toString());
        return result;
    }

    // TODO this solves it for a single obstacle but not for lines
    public List<Point> generateSolution3(Assignment3 assignment3) {
        List<Point> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        // same as in Solution2 but now the Ys are the same
        double angleX1 = Math.atan2(assignment3.getObstacle().getY(), assignment3.getObstacle().getX1());
        double angleX2 = Math.atan2(assignment3.getObstacle().getY(), assignment3.getObstacle().getX2());

        double lower = Math.min(angleX1, angleX2);
        double higher = Math.max(angleX1, angleX2);

        assignment3.getPoints().forEach(point -> {
            double pointAngle = Math.atan2(point.getY(), point.getX());

            // check whether the point is inside the two angles
            boolean withinAngleRange = pointAngle >= lower && pointAngle <= higher;
            // this only checks whether both Ys are below or above 0
            // should this not check whether the Y of the point is above / below the Y of the obstacle?
            boolean sameSideOfY = (assignment3.getObstacle().getY() < 0) == (point.getY() < 0);

            // this would make more sense
            int obstacleY = assignment3.getObstacle().getY();
            int pointY = point.getY();
            boolean clearsTheLine = (pointY < 0 && pointY > obstacleY) || (pointY > 0 && pointY < obstacleY);

            if (!withinAngleRange || !sameSideOfY) {
                result.add(point);
                stringBuilder.append(point.getX()).append(" ").append(point.getY()).append(" ");
            }
        });

        log.info("Filtered Points: " + stringBuilder.toString());
        return result;
    }

    // TODO this solves it for a list of obstacles but not for lines
    public List<Point> generateSolution4(Assignment4 assignment4) {
        List<Point> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        List<Obstacle> obstacles = assignment4.getObstacles();

        for (Point point : assignment4.getPoints()) {
            boolean isBehindAnyObstacle = false;

            for (Obstacle obstacle : obstacles) {
                // again, Y is the same and line goes through it
                double angleX1 = Math.atan2(obstacle.getY(), obstacle.getX1());
                double angleX2 = Math.atan2(obstacle.getY(), obstacle.getX2());

                double lower = Math.min(angleX1, angleX2);
                double higher = Math.max(angleX1, angleX2);

                double pointAngle = Math.atan2(point.getY(), point.getX());

                boolean withinAngleRange = pointAngle > lower && pointAngle < higher;
                boolean sameSideOfY = (obstacle.getY() < 0) == (point.getY() < 0);

                // this also improves same as above
                if (withinAngleRange && sameSideOfY && Math.abs(obstacle.getY()) < Math.abs(point.getY())) {
                    isBehindAnyObstacle = true;
                    break;
                }
            }

            if (!isBehindAnyObstacle) {
                result.add(point);
                stringBuilder.append(point.getX()).append(" ").append(point.getY()).append(" ");
                pointRepository.save(point);
            }
        }

        log.info("Filtered Points: " + stringBuilder.toString());
        return result;
    }




    public CoordinateSystem fetchInputJson() throws JsonProcessingException {
        CoordinateSystem coordinateSystem = objectMapper.readValue(requestInputJsonAsString, CoordinateSystem.class);
        return coordinateSystemRepository.save(coordinateSystem);
    }

    public CoordinateSystem create() {
        CoordinateSystem coordinateSystem = new CoordinateSystem();
        Map<String, Point> obstacles = new HashMap<>();
        Point pointA = new Point(4, -6);
        Point pointB = new Point(-3, -5);
        pointRepository.save(pointA);
        pointRepository.save(pointB);
        obstacles.put("pointA", pointA);
        obstacles.put("pointB", pointB);
        coordinateSystem.setObstacle(obstacles);

        List<Point> targets = new ArrayList<Point>();
        Point point1 = new Point(-1, -4);
        Point point2 = new Point(-5, -9);
        Point point3 = new Point(6, -6);
        Point point4 = new Point(6, 8);
        pointRepository.save(point1);
        pointRepository.save(point2);
        pointRepository.save(point3);
        pointRepository.save(point4);
        targets.add(point1);
        targets.add(point2);
        targets.add(point3);
        targets.add(point4);
        coordinateSystem.setTargets(targets);

        return coordinateSystemRepository.save(coordinateSystem);
    }

}

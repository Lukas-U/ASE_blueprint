package com.tuwien.ASE_blueprint.service;


import com.tuwien.ASE_blueprint.model.planetsAndPortals.Galaxy;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.PathWithCost;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.Planet;
import com.tuwien.ASE_blueprint.model.planetsAndPortals.Portal;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlanetService {
    public Set<Integer> generateSolution1(Galaxy galaxy, int startPlanetId) {
        // Reachability using BFS
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        // add the startPlanet to queue and visited
        queue.add(startPlanetId);
        visited.add(startPlanetId);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            // for each portal of the planet, visit its target planet
            // TODO should this not check if a planet was already visited?
            for (Portal portal : galaxy.getPlanet(current).getPortals()) {
                if (visited.add(portal.getTargetPlanetId())) {
                    queue.add(portal.getTargetPlanetId());
                }
            }
        }
        return visited;
    }

    public boolean generateSolution2(Galaxy galaxy) {
        // Strongly Connected
        for (Planet planet : galaxy.getPlanets().values()) {
            // for each planet, check if all other planets are reachable
            if (generateSolution1(galaxy, planet.getId()).size() != galaxy.getPlanets().size()) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> generateSolution3(Galaxy galaxy, int source, int target) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        PriorityQueue<Portal> queue = new PriorityQueue<>(Comparator.comparingInt(Portal::getWeight));
        Set<Integer> visited = new HashSet<>();

        galaxy.getPlanets().keySet().forEach(planetId -> distances.put(planetId, Integer.MAX_VALUE));
        distances.put(source, 0);
        queue.add(new Portal(source, 0));

        while (!queue.isEmpty()) {
            Portal current = queue.poll();
            int currentPlanetId = current.getTargetPlanetId();
            if (visited.contains(currentPlanetId)) continue;
            visited.add(currentPlanetId);
            int currentDistance = distances.get(currentPlanetId);

            for (Portal portal : galaxy.getPlanet(currentPlanetId).getPortals()) {
                int adjPlanetId = portal.getTargetPlanetId();
                if (visited.contains(adjPlanetId)) continue;

                int newDistance = currentDistance + portal.getWeight();
                if (newDistance < distances.get(adjPlanetId)) {
                    distances.put(adjPlanetId, newDistance);
                    predecessors.put(adjPlanetId, currentPlanetId);
                    queue.add(new Portal(adjPlanetId, newDistance));
                }
            }
        }

        return reconstructPath(predecessors, source, target);
    }

    public PathWithCost generateSolution4(Galaxy galaxy) {
        PathWithCost mostExpensivePath = new PathWithCost(Collections.emptyList(), 0);

        for (Integer startPlanetId : galaxy.getPlanets().keySet()) {
            for (Integer targetPlanetId : galaxy.getPlanets().keySet()) {
                if (!startPlanetId.equals(targetPlanetId)) {
                    List<Integer> path = generateSolution3(galaxy, startPlanetId, targetPlanetId);
                    int cost = calculatePathCost(galaxy, path);
                    if (cost > mostExpensivePath.getCost()) {
                        mostExpensivePath = new PathWithCost(path, cost);
                    }
                }
            }
        }

        return mostExpensivePath;
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> predecessors, int source, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        for (Integer at = target; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }
        return path.getFirst() == source ? path : Collections.emptyList(); // Return the path if valid
    }

    private int calculatePathCost(Galaxy galaxy, List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            for (Portal portal : galaxy.getPlanet(path.get(i)).getPortals()) {
                if (portal.getTargetPlanetId() == path.get(i + 1)) {
                    cost += portal.getWeight();
                    break;
                }
            }
        }
        return cost;
    }

}

package com.tuwien.ASE_blueprint.model.planetsAndPortals;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Galaxy {
    private Map<Integer, Planet> planets;

    public Galaxy() {
        this.planets = new HashMap<>();
    }

    public void addPlanet(Planet planet) {

        planets.put(planet.getId(), planet);
    }

    public Planet getPlanet(int id) {

        return planets.get(id);
    }
}

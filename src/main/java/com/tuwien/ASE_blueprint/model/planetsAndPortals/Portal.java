package com.tuwien.ASE_blueprint.model.planetsAndPortals;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Portal {
    private int targetPlanetId;
    private int weight;
}

package com.tuwien.ASE_blueprint.model.planetsAndPortals;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PathWithCost {
    private List<Integer> path;
    private int cost;
}

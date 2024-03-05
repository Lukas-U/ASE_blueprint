package com.tuwien.ASE_blueprint.model.dijkstra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    int target;
    int weight;
}

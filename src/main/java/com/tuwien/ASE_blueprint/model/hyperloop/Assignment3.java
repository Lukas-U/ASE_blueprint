package com.tuwien.ASE_blueprint.model.hyperloop;

import com.tuwien.ASE_blueprint.model.Obstacle;
import com.tuwien.ASE_blueprint.model.Point;
import lombok.Data;

import java.util.List;

@Data
public class Assignment3 {
    private Obstacle obstacle;
    private List<Point> points;
}

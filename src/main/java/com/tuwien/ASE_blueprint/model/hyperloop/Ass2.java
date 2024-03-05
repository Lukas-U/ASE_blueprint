package com.tuwien.ASE_blueprint.model.hyperloop;

import com.tuwien.ASE_blueprint.model.Point;
import lombok.Data;

import java.util.List;

@Data
public class Ass2 {
    private Point startPoint;
    private Point endPoint;
    private List<Point> points;
}

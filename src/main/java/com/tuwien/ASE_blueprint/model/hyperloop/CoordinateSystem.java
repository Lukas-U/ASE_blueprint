package com.tuwien.ASE_blueprint.model.hyperloop;

import com.tuwien.ASE_blueprint.model.Point;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Map;

@Entity(name = "coordinate_system")
public class CoordinateSystem {

    @Id
    @SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="yourSequenceGenerator")
    private Long id;

    @OneToMany(targetEntity = Point.class)
    private Map<String, Point> obstacle;

    @OneToMany(targetEntity = Point.class)
    private Collection<Point> targets;

    public Long getId() {
        return id;
    }

    public Map<String, Point> getObstacle() {
        return obstacle;
    }

    public void setObstacle(Map<String, Point> obstacle) {
        this.obstacle = obstacle;
    }

    public Collection<Point> getTargets() {
        return targets;
    }

    public void setTargets(Collection<Point> targets) {
        this.targets = targets;
    }
}

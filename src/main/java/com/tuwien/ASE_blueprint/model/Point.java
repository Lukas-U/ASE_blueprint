package com.tuwien.ASE_blueprint.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity()
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

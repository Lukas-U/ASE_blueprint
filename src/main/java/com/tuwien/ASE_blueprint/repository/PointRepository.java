package com.tuwien.ASE_blueprint.repository;

import com.tuwien.ASE_blueprint.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {}

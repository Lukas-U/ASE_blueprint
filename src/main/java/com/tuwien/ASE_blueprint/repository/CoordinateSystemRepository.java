package com.tuwien.ASE_blueprint.repository;

import com.tuwien.ASE_blueprint.model.hyperloop.CoordinateSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateSystemRepository extends JpaRepository<CoordinateSystem, Long> {}

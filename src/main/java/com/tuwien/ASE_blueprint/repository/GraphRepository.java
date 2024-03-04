package com.tuwien.ASE_blueprint.repository;

import com.tuwien.ASE_blueprint.model.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {
}

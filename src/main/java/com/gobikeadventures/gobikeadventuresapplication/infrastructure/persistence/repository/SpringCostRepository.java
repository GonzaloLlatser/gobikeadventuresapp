package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringCostRepository extends JpaRepository<Cost, Long> {
}

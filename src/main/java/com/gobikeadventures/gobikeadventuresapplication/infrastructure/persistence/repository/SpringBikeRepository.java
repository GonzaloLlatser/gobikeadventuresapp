package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBikeRepository extends JpaRepository<Bike, Long> {
}

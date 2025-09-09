package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringExperienceRepository extends JpaRepository<Experience, Long> {
}

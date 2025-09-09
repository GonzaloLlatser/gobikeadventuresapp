package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringPersonalDataRepository extends JpaRepository<PersonalData, Long> {
}

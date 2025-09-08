package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
}

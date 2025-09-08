package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringRoleRepository extends JpaRepository<Role, Long> {
}
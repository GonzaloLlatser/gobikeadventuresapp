package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringUserRepository extends JpaRepository<User, UUID> {
}

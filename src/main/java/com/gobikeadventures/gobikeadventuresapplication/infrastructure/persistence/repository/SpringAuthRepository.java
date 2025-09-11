package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringAuthRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
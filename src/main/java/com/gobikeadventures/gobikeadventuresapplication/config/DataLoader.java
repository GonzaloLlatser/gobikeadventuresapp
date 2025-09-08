package com.gobikeadventures.gobikeadventuresapplication.config;

import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Role;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringRoleRepository;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  private final SpringUserRepository springUserRepository;
  private final SpringRoleRepository springRoleRepository;
  private final PasswordEncoderPort passwordEncoderPort;

  public DataLoader(UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort, SpringUserRepository springUserRepository, SpringRoleRepository springRoleRepository, PasswordEncoderPort passwordEncoderPort) {
    this.springUserRepository = springUserRepository;
    this.springRoleRepository = springRoleRepository;
    this.passwordEncoderPort = passwordEncoderPort;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    // Create roles to populate the database
    Role adminRole = Role.builder()
      .name("ADMIN")
      .permissionDescription("Admin full access")
      .build();

    Role userRole = Role.builder()
      .name("USER")
      .permissionDescription("Normal user access")
      .build();

    Role managerRole = Role.builder()
      .name("MANAGER")
      .permissionDescription("Manager user access")
      .build();

    springRoleRepository.saveAll(Arrays.asList(adminRole, userRole));

    // Create users to populate the database
    User admin = User.builder()
      .email("admin@gobike.com")
      .password(passwordEncoderPort.encode("admin123"))
      .rol(adminRole)
      .build();

    User user = User.builder()
      .email("user@gobike.com")
      .password(passwordEncoderPort.encode("user123"))
      .rol(userRole)
      .build();

    springUserRepository.saveAll(Arrays.asList(admin, user));

    System.out.println("Base de datos inicializada!");
  }
}

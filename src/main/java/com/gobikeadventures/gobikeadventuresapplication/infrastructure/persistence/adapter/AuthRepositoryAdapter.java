package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.AuthRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper.AuthPersistenceMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class AuthRepositoryAdapter implements AuthRepositoryPort {

  private final SpringAuthRepository springAuthRepository;
  private final AuthPersistenceMapper authPersistenceMapper;

  public AuthRepositoryAdapter(SpringAuthRepository springAuthRepository, AuthPersistenceMapper authPersistenceMapper, PasswordEncoderPort passwordEncoderPort) {
    this.springAuthRepository = springAuthRepository;
    this.authPersistenceMapper = authPersistenceMapper;
  }

  @Override
  public Optional<AuthDO> findByEmail(String email) {

    User entity = springAuthRepository.findByEmail(email);

    if (entity == null) {
      return Optional.empty();
    }

    AuthDO domain = authPersistenceMapper.toDomain(entity);
    return Optional.of(domain);
  }
}

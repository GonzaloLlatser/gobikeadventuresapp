package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final SpringUserRepository springUserRepository;
  private final UserPersistenceMapper userPersistenceMapper;

  public UserRepositoryAdapter(SpringUserRepository springUserRepository, UserPersistenceMapper userPersistenceMapper) {
    this.springUserRepository = springUserRepository;
    this.userPersistenceMapper = userPersistenceMapper;
  }

  @Override
  public com.gobikeadventures.gobikeadventuresapplication.domain.model.User save(com.gobikeadventures.gobikeadventuresapplication.domain.model.User user) {
    User userEntity = userPersistenceMapper.toEntity(user);
    return userPersistenceMapper.toDomain(springUserRepository.save(userEntity));
  }
}
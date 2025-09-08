package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.User;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.UserEntity;
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
  public User save(User user) {
    UserEntity userEntity = userPersistenceMapper.toEntity(user);
    return userPersistenceMapper.toDomain(springUserRepository.save(userEntity));
  }
}
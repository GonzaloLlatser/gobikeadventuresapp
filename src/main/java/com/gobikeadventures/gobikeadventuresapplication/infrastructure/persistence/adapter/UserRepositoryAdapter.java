package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final SpringUserRepository springUserRepository;
  private final UserPersistenceMapper userPersistenceMapper;

  public UserRepositoryAdapter(SpringUserRepository springUserRepository, UserPersistenceMapper userPersistenceMapper) {
    this.springUserRepository = springUserRepository;
    this.userPersistenceMapper = userPersistenceMapper;
  }

  @Override
  public UserDO save(UserDO user) {
    User userEntity = userPersistenceMapper.toEntity(user);

    return userPersistenceMapper.toDomain(springUserRepository.save(userEntity));
  }

  @Override
  public UserDO getUserById(Long id) {
    return springUserRepository.findById(id)
      .map(userPersistenceMapper::toDomain)
      .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  @Override
  public List<UserDO> getAll() {
    return springUserRepository.findAll()
      .stream().map(userPersistenceMapper::toDomain)
      .collect(Collectors.toList());
  }
}
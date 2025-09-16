package com.gobikeadventures.gobikeadventuresapplication.application.services.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.UserServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepositoryPort;
  private final RoleRepositoryPort roleRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;

  public UserService(UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
    this.userRepositoryPort = userRepositoryPort;
    this.roleRepositoryPort = roleRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;
  }

  @Override
  public UserDO add(UserDO user, Long roleId) {
    // Password hash
    String hashedPassword = passwordEncoderPort.encode(user.getPassword());
    user.setPassword(hashedPassword);

    RoleDO role = roleRepositoryPort.findById(String.valueOf(roleId))
      .orElseThrow(() -> new RuntimeException("Rol undefined"));
    user.setRol(role);

    return userRepositoryPort.save(user);
  }

  @Override
  public UserDO getUserById(Long id) {
    return userRepositoryPort.getUserById(id);
  }

  @Override
  public List<UserDO> getAll() {
    return userRepositoryPort.getAll();
  }
}

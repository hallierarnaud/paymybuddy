package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class MapDAO {

  public User updateUserWithUserEntity (User user, UserEntity userEntity) {
    user.setId(userEntity.getId());
    user.setLastname(userEntity.getLastname());
    user.setFirstname(userEntity.getFirstname());
    user.setBirthdate(userEntity.getBirthdate());
    return user;
  }

  public UserEntity updateUserEntityWithUser (UserEntity userEntity, User user) {
    userEntity.setId(user.getId());
    userEntity.setLastname(user.getLastname());
    userEntity.setFirstname(user.getFirstname());
    userEntity.setBirthdate(user.getBirthdate());
    return userEntity;
  }

}

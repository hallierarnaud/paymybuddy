package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class UserDAO {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  MapDAO mapDAO;

  public User findById(Long id) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("user " + id + " doesn't exist"));
    User user = new User();
    mapDAO.updateUserWithUserEntity(user, userEntity);
    return user;
  }

  public List<User> findAll() {
    List<UserEntity> userEntities =  StreamSupport.stream(userRepository.findAll().spliterator(),false)
            .collect(Collectors.toList());
    return userEntities.stream().map((userEntity) -> {
      User user = new User();
      mapDAO.updateUserWithUserEntity(user, userEntity);
      return user;
    }).collect(Collectors.toList());
  }

  public User updateUser(Long id, User user) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("user " + id + " doesn't exist"));
    mapDAO.updateUserEntityWithUser(userEntity, user);
    userRepository.save(userEntity);
    return user;
  }

  public User addUser(User user) {
    UserEntity userEntity = new UserEntity();
    mapDAO.updateUserEntityWithUser(userEntity, user);
    userRepository.save(userEntity);
    return user;
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }

  public boolean existById(Long id) {
    return userRepository.existsById(id);
  }

}

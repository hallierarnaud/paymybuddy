package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.UserRequest;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.DAO.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityExistsException;

import lombok.Data;

@Data
@Service
public class UserService {

  @Autowired
  private UserDAO userDAO;

  public List<User> getUsers() {
    return StreamSupport.stream(userDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public User getUser(final Long id) {
    if (userDAO.findById(id) == null) {
      throw new NoSuchElementException("user " + id + " doesn't exist");
    }
    return userDAO.findById(id);
  }

  public User updateUser(final Long id, UserRequest userRequest) {
    if (userDAO.findById(id) == null) {
      throw new NoSuchElementException("user " + id + " doesn't exist");
    }
    User user = userDAO.findById(id);
    user.setId(id);
    user.setLastname(userRequest.getLastname());
    user.setFirstname(userRequest.getFirstname());
    user.setBirthdate(userRequest.getBirthdate());
    return userDAO.updateUser(id, user);
  }

  public User addUser(UserRequest userRequest) {
    if (userDAO.existById(userRequest.getId())) {
      throw new EntityExistsException("user " + userRequest.getId() + " already exists");
    }
    User user = new User();
    user.setId(userRequest.getId());
    user.setLastname(userRequest.getLastname());
    user.setFirstname(userRequest.getFirstname());
    user.setBirthdate(userRequest.getBirthdate());
    return userDAO.addUser(user);
  }

  public void deleteUser(final Long id) {
    if (!userDAO.existById(id)) {
      throw new NoSuchElementException("user " + id + " doesn't exist");
    }
    userDAO.deleteUserById(id);
  }

}

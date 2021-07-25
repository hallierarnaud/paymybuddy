package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.UserRequest;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> getPersons() {
    return userService.getUsers();
  }

  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable("id") long id) {
    try {
      return userService.getUser(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user " + id + " doesn't exist");
    }
  }

  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable("id") long id, @RequestBody UserRequest userRequest) {
    try {
      return userService.updateUser(id, userRequest);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "user " + id + " doesn't exist");
    }
  }

  @PostMapping("/users")
  public User addUser(@RequestBody UserRequest userRequest) {
    try {
      return userService.addUser(userRequest);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "user " + userRequest.getId() + " already exists");
    }
  }

  @DeleteMapping("/users/{id}")
  public void deleteUserById(@PathVariable("id") long id) {
    try {
      userService.deleteUser(id);
      throw new ResponseStatusException(HttpStatus.OK, "user " + id + " was deleted");
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user " + id + " doesn't exist");
    }
  }

}

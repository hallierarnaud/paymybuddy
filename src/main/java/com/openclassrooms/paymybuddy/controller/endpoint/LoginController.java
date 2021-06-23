package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.LoginRequest;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

@RestController
@CrossOrigin(origins="*")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @GetMapping("/logins/{id}")
  public Login getLoginById(@PathVariable("id") long id) {
    try {
      return loginService.getLogin(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "login " + id + " doesn't exist");
    }
  }

  @PostMapping("/logins")
  public Login addLogin(@RequestBody Login login) {
    try {
      return loginService.addLogin(login);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email " + login.getEmail() + " already exists");
    }
  }

}

package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.LoginRequest;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import lombok.Data;

@Data
@Service
public class LoginService {

  @Autowired
  private LoginDAO loginDAO;

  public Login getLogin(final Long id) {
    if (loginDAO.findById(id) == null) {
      throw new NoSuchElementException("login " + id + " doesn't exist");
    }
    return loginDAO.findById(id);
  }

  public Login addLogin (LoginRequest loginRequest) {
    if (loginDAO.existsByEmail(loginRequest.getEmail())) {
      throw new EntityExistsException("email " + loginRequest.getEmail() + " already exists");
    }
    Login login = new Login();
    login.setEmail(loginRequest.getEmail());
    login.setPassword(loginRequest.getPassword());
    return loginDAO.addLogin(login);
  }

}

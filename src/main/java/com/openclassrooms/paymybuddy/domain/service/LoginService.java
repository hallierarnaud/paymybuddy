package com.openclassrooms.paymybuddy.domain.service;

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

  public Login addLogin (Login login) {
    if (loginDAO.existsByEmail(login.getEmail())) {
      throw new EntityExistsException("email " + login.getEmail() + " already exists");
    }
    return loginDAO.addLogin(login);
  }

}

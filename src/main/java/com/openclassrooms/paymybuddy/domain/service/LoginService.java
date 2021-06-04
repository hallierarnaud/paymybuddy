package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

  public Login addLogin (String email, String password) {
    /*if (loginDAO.existByEmail(email) {
      throw new EntityExistsException("email " + email + " already exists");
    }*/
    Login login = new Login();
    login.setEmail(email);
    login.setPassword(password);
    return loginDAO.addLogin(login);
  }

}

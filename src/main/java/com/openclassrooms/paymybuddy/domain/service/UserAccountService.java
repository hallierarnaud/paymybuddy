package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;

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
public class UserAccountService {

  @Autowired
  private UserAccountDAO userAccountDAO;

  @Autowired
  private LoginDAO loginDAO;

  public List<UserAccount> getUserAccounts() {
    return StreamSupport.stream(userAccountDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public UserAccount checkUserAccount(Login login) {
    if (userAccountDAO.findByEmailAndPassword(login) == null) {
      throw new NoSuchElementException("email " + login.getEmail() + " doesn't exist or password is false");
    }
    return userAccountDAO.findByEmailAndPassword(login);
  }

  public UserAccount getUserAccountByLogin(final String email) {
    if (userAccountDAO.findByEmail(email) == null) {
      throw new NoSuchElementException("email " + email + " doesn't exist or password is false");
    }
    return userAccountDAO.findByEmail(email);
  }

  public UserAccount addUserAccount (UserAccount userAccount) {
    if (loginDAO.existsByEmail(userAccount.getEmail())) {
      throw new EntityExistsException("email " + userAccount.getEmail() + " already exists");
    }
    return userAccountDAO.addUserAccount(userAccount);
  }

}

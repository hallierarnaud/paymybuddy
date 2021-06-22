package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountRequest;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class UserAccountService {

  @Autowired
  private UserAccountDAO userAccountDAO;

  public UserAccount getUserAccount(final String email) {
    if (userAccountDAO.findByEmail(email) == null) {
      throw new NoSuchElementException("email " + email + " doesn't exist");
    }
    return userAccountDAO.findByEmail(email);
  }

  public UserAccount addUserAccount (UserAccountRequest userAccountRequest) {
    /*if (userAccountDAO.existsByEmail(userAccountRequest.getEmail())) {
      throw new EntityExistsException("email " + userAccountRequest.getEmail() + " already exists");
    }*/
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail(userAccountRequest.getEmail());
    userAccount.setPassword(userAccountRequest.getPassword());
    userAccount.setLastname(userAccountRequest.getLastname());
    userAccount.setFirstname(userAccountRequest.getFirstname());
    userAccount.setBirthdate(userAccountRequest.getBirthdate());
    userAccount.setBalance(userAccountRequest.getBalance());
    userAccount.setIban(userAccountRequest.getIban());
    return userAccountDAO.addUserAccount(userAccount);
  }
}

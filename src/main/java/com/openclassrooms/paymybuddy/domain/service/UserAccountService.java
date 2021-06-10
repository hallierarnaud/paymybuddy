package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountRequest;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class UserAccountService {

  @Autowired
  private UserAccountDAO userAccountDAO;

  public UserAccount addUserAccount (UserAccountRequest userAccountRequest) {
    /*if (userAccountDAO.existsByEmail(userAccountRequest.getEmail())) {
      throw new EntityExistsException("email " + userAccountRequest.getEmail() + " already exists");
    }*/
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail(userAccountRequest.getEmail());
    userAccount.setPassword(userAccountRequest.getPassword());
    return userAccountDAO.addUserAccount(userAccount);
  }
}

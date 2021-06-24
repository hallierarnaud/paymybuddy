package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.Data;

@Data
@Service
public class UserAccountService {

  @Autowired
  private UserAccountDAO userAccountDAO;

  public UserAccount getUserAccount(final String email, final String password) {
    if (userAccountDAO.findByEmailAndPassword(email, password) == null) {
      throw new NoSuchElementException("email " + email + " doesn't exist or password is false");
    }
    return userAccountDAO.findByEmailAndPassword(email, password);
  }

  public List<UserAccount> getUserAccounts() {
    return StreamSupport.stream(userAccountDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public UserAccount addUserAccount (UserAccount userAccount) {
    /*if (userAccountDAO.existsByEmail(userAccountRequest.getEmail())) {
      throw new EntityExistsException("email " + userAccountRequest.getEmail() + " already exists");
    }*/
    /*UserAccount userAccount = new UserAccount();
    userAccount.setEmail(userAccountRequest.getEmail());
    userAccount.setPassword(userAccountRequest.getPassword());
    userAccount.setLastname(userAccountRequest.getLastname());
    userAccount.setFirstname(userAccountRequest.getFirstname());
    userAccount.setBirthdate(userAccountRequest.getBirthdate());
    userAccount.setBalance(userAccountRequest.getBalance());
    userAccount.setIban(userAccountRequest.getIban());*/
    return userAccountDAO.addUserAccount(userAccount);
  }
}

package com.openclassrooms.paymybuddy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

import lombok.Data;

@Data
@Service
public class AccountCreationService {

  @Autowired
  private LoginService loginService;

  @Autowired
  private UserService userService;

  @Autowired
  private InputReader inputReader;

  public void createLogin(String email, String password) {
    loginService.addLogin(email, password);
  }

  public void createUser(String lastname, String firstname, String birthdate) throws ParseException {
    userService.addUserThroughCommandLine(lastname, firstname, birthdate);
  }



}

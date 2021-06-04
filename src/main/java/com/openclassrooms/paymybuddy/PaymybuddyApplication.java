package com.openclassrooms.paymybuddy;

import com.openclassrooms.paymybuddy.domain.service.AccountCreationService;
import com.openclassrooms.paymybuddy.domain.service.InputReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@SpringBootApplication
@RestController
public class PaymybuddyApplication implements ApplicationRunner {

  @Autowired
  private AccountCreationService accountCreationService;

  @Autowired
  private InputReader inputReader;

  public static void main(String[] args) {
    SpringApplication.run(PaymybuddyApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws ParseException {
    //accountCreationService.createLogin(inputReader.readEmail(), inputReader.readPassword());
    accountCreationService.createUser(inputReader.readLastname(), inputReader.readFirstName(), inputReader.readBirthdate());
  }

}

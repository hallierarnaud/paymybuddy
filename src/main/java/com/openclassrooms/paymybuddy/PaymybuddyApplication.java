package com.openclassrooms.paymybuddy;

import com.openclassrooms.paymybuddy.domain.service.InputReader;
import com.openclassrooms.paymybuddy.domain.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PaymybuddyApplication implements ApplicationRunner {

  @Autowired
  private LoginService loginService;

  @Autowired
  private InputReader inputReader;

  public static void main(String[] args) {
    SpringApplication.run(PaymybuddyApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) {
    loginService.addLogin(inputReader.readEmail(), inputReader.readPassword());
  }

}

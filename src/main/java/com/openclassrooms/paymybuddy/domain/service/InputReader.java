package com.openclassrooms.paymybuddy.domain.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

import lombok.Data;

@Data
@Service
public class InputReader {

  private static Scanner scanner = new Scanner(System.in, "UTF-8");
  private String furnishedEmail;
  private String furnishedPassword;

  public String readEmail() {
    try {
      System.out.println("To create a account, please enter an email address.");
      String inputEmail = scanner.nextLine();
      if (inputEmail == null || inputEmail.trim().length() == 0) {
        throw new IllegalArgumentException("Invalid input provided");
      }
      return inputEmail;
    } catch (Exception e) {
      System.out.println("Error reading input. Please enter a valid string email.");
      throw e;
    }
  }

  public String readPassword() {
    try {
      System.out.println("Please enter a password.");
      String inputPassword = scanner.nextLine();
      if (inputPassword == null || inputPassword.trim().length() == 0) {
        throw new IllegalArgumentException("Invalid input provided");
      }
      return inputPassword;
    } catch (Exception e) {
      System.out.println("Error reading input. Please enter a valid string password.");
      throw e;
    }
  }

}

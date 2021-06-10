package com.openclassrooms.paymybuddy.controller.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccountRequest {

  private String email;
  private String password;

  private String lastname;
  private String firstname;
  private Date birthdate;

}

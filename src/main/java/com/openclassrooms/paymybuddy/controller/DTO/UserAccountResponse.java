package com.openclassrooms.paymybuddy.controller.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccountResponse {

  private Long loginId;
  private String email;
  private String password;

  private Long userId;
  private String lastname;
  private String firstname;
  private Date birthdate;


}

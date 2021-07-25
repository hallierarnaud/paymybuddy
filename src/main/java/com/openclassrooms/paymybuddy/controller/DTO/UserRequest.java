package com.openclassrooms.paymybuddy.controller.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {

  private Long id;
  private String lastname;
  private String firstname;
  private Date birthdate;

}

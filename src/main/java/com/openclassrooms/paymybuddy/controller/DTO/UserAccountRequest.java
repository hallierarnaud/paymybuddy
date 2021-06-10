package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class UserAccountRequest {

  private String email;
  private String password;

}

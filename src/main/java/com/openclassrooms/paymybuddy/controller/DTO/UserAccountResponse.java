package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class UserAccountResponse {

  private Long loginId;
  private String email;
  private String password;


}

package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class Login {

  private Long id;
  private String email;
  private String password;

}

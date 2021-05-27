package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class Login {

  private long id;
  private String email;
  private String password;

}

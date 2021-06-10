package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class UserAccount {

  private Long loginId;
  private String email;
  private String password;

}

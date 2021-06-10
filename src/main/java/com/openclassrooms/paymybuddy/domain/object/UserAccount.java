package com.openclassrooms.paymybuddy.domain.object;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccount {

  private Long loginId;
  private String email;
  private String password;

  private Long userId;
  private String lastname;
  private String firstname;
  private Date birthdate;

}

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

  private Long internalAccountId;
  private Double balance;

  private Long externalAccountId;
  private String iban;

}

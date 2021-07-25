package com.openclassrooms.paymybuddy.domain.object;

import java.util.Date;

import lombok.Data;

@Data
public class User {

  private Long id;
  private String lastname;
  private String firstname;
  private Date birthdate;

}

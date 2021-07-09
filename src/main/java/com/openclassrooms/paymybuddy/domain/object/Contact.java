package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class Contact {

  private Long relationId;
  private Long userId;
  private Long contactId;
  private String contactEmail;

}

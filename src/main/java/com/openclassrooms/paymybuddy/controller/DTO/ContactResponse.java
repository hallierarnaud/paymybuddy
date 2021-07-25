package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class ContactResponse {

  private Long relationId;
  private Long userId;
  private Long contactId;
  private String contactEmail;

}

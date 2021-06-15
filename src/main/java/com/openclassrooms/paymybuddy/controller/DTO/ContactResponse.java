package com.openclassrooms.paymybuddy.controller.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ContactResponse {

  private Long userId;
  private List<Long> contactIdList;

}

package com.openclassrooms.paymybuddy.controller.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContactRequest {

  private Long userId;
  private List<Long> contactIdList = new ArrayList<>();

}

package com.openclassrooms.paymybuddy.domain.object;

import java.util.List;

import lombok.Data;

@Data
public class Contact {

  private List<Long> relationId;
  private Long userId;
  private List<Long> contactIdList;

}

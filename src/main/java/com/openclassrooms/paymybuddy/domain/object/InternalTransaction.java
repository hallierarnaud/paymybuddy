package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class InternalTransaction {

  private long id;
  private String description;
  private Double transferredAmount;

}

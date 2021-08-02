package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class ExternalTransactionRequest {

  private String description;
  private Double transferredAmount;
  private Long internalAccountId;
  private Long externalAccountId;

}

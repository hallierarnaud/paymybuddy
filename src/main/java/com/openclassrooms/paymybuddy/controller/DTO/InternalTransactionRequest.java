package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class InternalTransactionRequest {

  private String description;
  private Double transferredAmount;
  private Long senderInternalAccountId;
  private Long recipientInternalAccountId;

}

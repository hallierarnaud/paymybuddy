package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class InternalTransactionResponse {

  private Long id;
  private String description;
  private Double transferredAmount;
  private Long senderInternalAccountId;
  private Long recipientInternalAccountId;
  private String recipientInternalAccountEmail;

}

package com.openclassrooms.paymybuddy.controller.DTO;

import lombok.Data;

@Data
public class ExternalTransactionResponse {

  private Long id;
  private Double internalAccountBalance;

}

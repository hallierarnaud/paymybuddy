package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class ExternalTransaction {

  private Long id;
  private String description;
  private Double transferredAmount;
  private InternalAccount internalAccount;
  private ExternalAccount externalAccount;

}

package com.openclassrooms.paymybuddy.domain.object;

import lombok.Data;

@Data
public class InternalTransaction {

  private Long id;
  private String description;
  private Double transferredAmount;
  private InternalAccount senderInternalAccount;
  private InternalAccount recipientInternalAccount;
  private Long senderInternalAccountId;
  private Long recipientInternalAccountId;

}

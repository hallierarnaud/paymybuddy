package com.openclassrooms.paymybuddy.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "internal_account")
public class InternalAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(columnDefinition = "balance", nullable = false)
  private Double balance;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @OneToOne(mappedBy = "internalAccountEntity")
  private ExternalTransactionEntity externalTransactionEntity;

  @OneToOne(mappedBy = "senderAccountEntity")
  private InternalTransactionEntity internalTransactionEntitySender;

  @OneToOne(mappedBy = "recipientAccountEntity")
  private InternalTransactionEntity internalTransactionEntityRecipient;

}

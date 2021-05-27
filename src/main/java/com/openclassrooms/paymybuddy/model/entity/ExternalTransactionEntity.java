package com.openclassrooms.paymybuddy.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "external_transaction")
public class ExternalTransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(columnDefinition = "description", length = 200)
  private String description;

  @Column(columnDefinition = "transferredAmount")
  private Double transferredAmount;

  @OneToOne
  @JoinColumn(columnDefinition = "internalaccount_id")
  private InternalAccountEntity internalAccountEntity;

  @OneToOne
  @JoinColumn(columnDefinition = "externalaccount_id")
  private ExternalAccountEntity externalAccountEntity;

}
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
@Table(name = "external_account")
public class ExternalAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(columnDefinition = "IBAN", nullable = false, length = 50)
  private String iban;

  @OneToOne
  @JoinColumn(columnDefinition = "user_id")
  private UserEntity userEntity;

  @OneToOne(mappedBy = "externalAccountEntity")
  private ExternalTransactionEntity externalTransactionEntity;

}

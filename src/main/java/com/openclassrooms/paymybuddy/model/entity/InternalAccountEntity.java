package com.openclassrooms.paymybuddy.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

  @OneToOne
  private UserEntity userEntity;

  @OneToMany(mappedBy = "internalAccountEntity")
  private List<ExternalTransactionEntity> externalTransactionEntities;

  @ManyToMany(mappedBy = "internalAccountEntities")
  private List<InternalTransactionEntity> internalTransactionEntities;

}

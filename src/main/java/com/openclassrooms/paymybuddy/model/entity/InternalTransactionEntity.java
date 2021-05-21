package com.openclassrooms.paymybuddy.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "internal_transaction")
public class InternalTransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(columnDefinition = "description", length = 200)
  private String description;

  @Column(columnDefinition = "transferredAmount")
  private Double transferredAmount;

  @ManyToMany
  private List<InternalAccountEntity> internalAccountEntities;

}

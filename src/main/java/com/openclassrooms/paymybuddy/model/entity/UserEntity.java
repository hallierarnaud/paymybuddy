package com.openclassrooms.paymybuddy.model.entity;

import java.util.Date;

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
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "lastname", nullable = false, length = 50)
  private String lastname;

  @Column(name = "firstname", nullable = false, length = 50)
  private String firstname;

  @Column(name = "birthdate", nullable = false)
  private Date birthdate;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "login_id")
  private LoginEntity loginEntity;

  @OneToOne(mappedBy = "userEntity")
  private ExternalAccountEntity externalAccountEntity;

  @OneToOne(mappedBy = "userEntity")
  private InternalAccountEntity internalAccountEntity;

}

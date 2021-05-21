package com.openclassrooms.paymybuddy.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(columnDefinition = "lastName", nullable = false, length = 50)
  private String lastname;

  @Column(columnDefinition = "firstName", nullable = false, length = 50)
  private String firstname;

  @Column(columnDefinition = "birthdate", nullable = false)
  private Date birthdate;

  @OneToOne
  @JoinColumn(columnDefinition = "login_id")
  private LoginEntity loginEntity;

  @ManyToMany(mappedBy = "userEntity")
  private List<UserContactEntity> userContactEntities;

  @OneToOne(mappedBy = "userEntity")
  private ExternalAccountEntity externalAccountEntity;

  @OneToOne(mappedBy = "userEntity")
  private InternalAccountEntity internalAccountEntity;

}

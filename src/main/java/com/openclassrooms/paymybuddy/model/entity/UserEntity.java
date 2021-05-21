package com.openclassrooms.paymybuddy.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}

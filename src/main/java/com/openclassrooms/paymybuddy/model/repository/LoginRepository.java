package com.openclassrooms.paymybuddy.model.repository;

import com.openclassrooms.paymybuddy.model.entity.LoginEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

  Boolean existsByEmail(String email);

}

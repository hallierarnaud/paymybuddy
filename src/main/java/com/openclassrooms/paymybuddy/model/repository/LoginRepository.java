package com.openclassrooms.paymybuddy.model.repository;

import com.openclassrooms.paymybuddy.model.entity.LoginEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

  Boolean existsByEmail(String email);

  Optional<LoginEntity> findByEmailAndPassword (String email, String password);

  Optional<LoginEntity> findByEmail (String email);

  Optional<LoginEntity> findByUserEntity_Id (Long userId);

}

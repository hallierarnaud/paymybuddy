package com.openclassrooms.paymybuddy.model.repository;

import com.openclassrooms.paymybuddy.model.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

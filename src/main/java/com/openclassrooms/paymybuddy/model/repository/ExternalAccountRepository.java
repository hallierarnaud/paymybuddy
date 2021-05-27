package com.openclassrooms.paymybuddy.model.repository;

import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalAccountRepository extends JpaRepository<ExternalAccountEntity, Long> {
}

package com.openclassrooms.paymybuddy.model.repository;

import com.openclassrooms.paymybuddy.model.entity.ContactEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}

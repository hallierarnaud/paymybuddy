package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.ExternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class ExternalTransactionDAO {

  @Autowired
  private ExternalTransactionRepository externalTransactionRepository;

  @Autowired
  MapDAO mapDAO;

  public ExternalTransaction findById(Long id) {
    ExternalTransactionEntity externalTransactionEntity = externalTransactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("external transaction " + id + " doesn't exist"));
    ExternalTransaction externalTransaction = new ExternalTransaction();
    mapDAO.updateExternalTransactionWithExternalTransactionEntity(externalTransaction, externalTransactionEntity);
    return externalTransaction;
  }

}

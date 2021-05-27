package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.repository.InternalTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class InternalTransactionDAO {

  @Autowired
  private InternalTransactionRepository internalTransactionRepository;

  @Autowired
  MapDAO mapDAO;

  public InternalTransaction findById(Long id) {
    InternalTransactionEntity internalTransactionEntity = internalTransactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("internal transaction " + id + " doesn't exist"));
    InternalTransaction internalTransaction = new InternalTransaction();
    mapDAO.updateInternalTransactionWithInternalTransactionEntity(internalTransaction, internalTransactionEntity);
    return internalTransaction;
  }

}

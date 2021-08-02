package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.ExternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.ExternalTransactionRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
public class ExternalTransactionDAO {

  @Autowired
  private ExternalTransactionRepository externalTransactionRepository;

  @Autowired
  private InternalAccountRepository internalAccountRepository;

  @Autowired
  private ExternalAccountRepository externalAccountRepository;

  @Autowired
  MapDAO mapDAO;

  public ExternalTransaction findById(Long id) {
    ExternalTransactionEntity externalTransactionEntity = externalTransactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("external transaction " + id + " doesn't exist"));
    ExternalTransaction externalTransaction = new ExternalTransaction();
    mapDAO.updateExternalTransactionWithExternalTransactionEntity(externalTransaction, externalTransactionEntity);
    return externalTransaction;
  }

  @Transactional
  public ExternalTransaction addExternalTransaction(ExternalTransaction externalTransaction) {
    ExternalTransactionEntity externalTransactionEntity = new ExternalTransactionEntity();
    externalTransactionEntity.setDescription(externalTransaction.getDescription());
    externalTransactionEntity.setTransferredAmount(externalTransaction.getTransferredAmount());
    InternalAccountEntity internalAccountEntity = internalAccountRepository.findById(externalTransaction.getInternalAccount().getId()).orElseThrow(() -> new NoSuchElementException("internal account " + externalTransaction.getInternalAccount().getId() + " doesn't exist"));
    externalTransactionEntity.setInternalAccountEntity(internalAccountEntity);
    externalTransactionEntity.setExternalAccountEntity(externalAccountRepository.findById(externalTransaction.getExternalAccount().getId()).orElseThrow(() -> new NoSuchElementException("external account " + externalTransaction.getExternalAccount().getId() + " doesn't exist")));
    externalTransactionRepository.save(externalTransactionEntity);

    internalAccountEntity.setBalance(externalTransaction.getInternalAccount().getBalance());
    internalAccountRepository.save(internalAccountEntity);

    externalTransaction.setId(externalTransactionEntity.getId());
    return externalTransaction;
  }

}

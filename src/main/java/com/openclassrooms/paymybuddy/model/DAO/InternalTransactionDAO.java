package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class InternalTransactionDAO {

  @Autowired
  private InternalTransactionRepository internalTransactionRepository;

  @Autowired
  private InternalAccountRepository internalAccountRepository;

  @Autowired
  MapDAO mapDAO;

  public List<InternalTransaction> findAllBySenderAccountId(Long senderAccountId) {
    List<InternalTransactionEntity> internalTransactionEntities = StreamSupport.stream(internalTransactionRepository.findInternalTransactionEntitiesBySenderAccountEntity_Id(senderAccountId).spliterator(),false)
            .collect(Collectors.toList());
    return internalTransactionEntities.stream().map((internalTransactionEntity) -> {
      InternalTransaction internalTransaction = new InternalTransaction();
      mapDAO.updateInternalTransactionWithInternalTransactionEntity(internalTransaction, internalTransactionEntity);
      return internalTransaction;
    }).collect(Collectors.toList());
  }

  @Transactional
  public InternalTransaction addInternalTransaction(InternalTransaction internalTransaction) {
    InternalTransactionEntity internalTransactionEntity = new InternalTransactionEntity();
    internalTransactionEntity.setDescription(internalTransaction.getDescription());
    internalTransactionEntity.setTransferredAmount(internalTransaction.getTransferredAmount());
    InternalAccountEntity senderInternalAccountEntity = internalAccountRepository.findById(internalTransaction.getSenderInternalAccount().getId()).orElseThrow(() -> new NoSuchElementException("internal account " + internalTransaction.getSenderInternalAccount().getId() + " doesn't exist"));
    internalTransactionEntity.setSenderAccountEntity(senderInternalAccountEntity);
    InternalAccountEntity recipientInternalAccountEntity = internalAccountRepository.findById(internalTransaction.getRecipientInternalAccount().getId()).orElseThrow(() -> new NoSuchElementException("internal account " + internalTransaction.getRecipientInternalAccount().getId() + " doesn't exist"));
    internalTransactionEntity.setRecipientAccountEntity(recipientInternalAccountEntity);
    internalTransactionRepository.save(internalTransactionEntity);

    senderInternalAccountEntity.setBalance(internalTransaction.getSenderInternalAccount().getBalance());
    internalAccountRepository.save(senderInternalAccountEntity);

    recipientInternalAccountEntity.setBalance(internalTransaction.getRecipientInternalAccount().getBalance());
    internalAccountRepository.save(recipientInternalAccountEntity);

    internalTransaction.setId(internalTransactionEntity.getId());
    return internalTransaction;
  }

}

package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    List<InternalTransactionEntity> internalTransactions = StreamSupport.stream(internalTransactionRepository.findInternalTransactionEntitiesBySenderAccountEntity_Id(senderAccountId).spliterator(),false)
            .collect(Collectors.toList());
    return internalTransactions.stream().map((internalTransactionEntity) -> {
      InternalTransaction internalTransaction = new InternalTransaction();
      internalTransaction.setId(internalTransactionEntity.getId());
      internalTransaction.setDescription(internalTransactionEntity.getDescription());
      internalTransaction.setTransferredAmount(internalTransactionEntity.getTransferredAmount());
      internalTransaction.setSenderInternalAccountId(internalTransactionEntity.getSenderAccountEntity().getId());
      internalTransaction.setRecipientInternalAccountId(internalTransactionEntity.getRecipientAccountEntity().getId());
      return internalTransaction;
    }).collect(Collectors.toList());
  }

  public InternalTransactionResponse addInternalTransaction(InternalTransaction internalTransaction) {
    InternalTransactionEntity internalTransactionEntity = new InternalTransactionEntity();
    internalTransactionEntity.setDescription(internalTransaction.getDescription());
    internalTransactionEntity.setTransferredAmount(internalTransaction.getTransferredAmount());
    InternalAccountEntity internalAccountEntityAsSender = internalAccountRepository.findById(internalTransaction.getSenderInternalAccountId()).orElseThrow(() -> new NoSuchElementException("internal account " + internalTransaction.getSenderInternalAccountId() + " doesn't exist"));
    internalTransactionEntity.setSenderAccountEntity(internalAccountEntityAsSender);
    InternalAccountEntity internalAccountEntityAsRecipient = internalAccountRepository.findById(internalTransaction.getRecipientInternalAccountId()).orElseThrow(() -> new NoSuchElementException("internal account " + internalTransaction.getRecipientInternalAccountId() + " doesn't exist"));
    internalTransactionEntity.setRecipientAccountEntity(internalAccountEntityAsRecipient);
    internalTransactionRepository.save(internalTransactionEntity);

    InternalTransactionResponse internalTransactionResponse = new InternalTransactionResponse();
    internalTransactionResponse.setId(internalTransactionEntity.getId());
    internalTransactionResponse.setDescription(internalTransactionEntity.getDescription());
    internalTransactionResponse.setTransferredAmount(internalTransactionEntity.getTransferredAmount());
    internalTransactionResponse.setSenderInternalAccountId(internalTransactionEntity.getSenderAccountEntity().getId());
    internalTransactionResponse.setRecipientInternalAccountId(internalTransactionEntity.getRecipientAccountEntity().getId());
    return internalTransactionResponse;
  }

}

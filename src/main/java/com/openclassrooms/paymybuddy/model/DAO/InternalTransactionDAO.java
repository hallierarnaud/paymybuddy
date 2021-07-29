package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
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
  MapDAO mapDAO;

  public List<InternalTransaction> findAllBySenderAccountId(Long senderAccountId) {
    List<InternalTransactionEntity> internalTransactions =  StreamSupport.stream(internalTransactionRepository.findInternalTransactionEntitiesBySenderAccountEntity_Id(senderAccountId).spliterator(),false)
            .collect(Collectors.toList());
    return internalTransactions.stream().map((internalTransactionEntity) -> {
      InternalTransaction internalTransaction = new InternalTransaction();
      internalTransaction.setId(internalTransactionEntity.getId());
      internalTransaction.setDescription(internalTransactionEntity.getDescription());
      internalTransaction.setTransferredAmount(internalTransaction.getTransferredAmount());
      internalTransaction.setSenderInternalAccountId(internalTransactionEntity.getSenderAccountEntity().getId());
      internalTransaction.setRecipientInternalAccountId(internalTransactionEntity.getSenderAccountEntity().getId());
      return internalTransaction;
    }).collect(Collectors.toList());
  }

}

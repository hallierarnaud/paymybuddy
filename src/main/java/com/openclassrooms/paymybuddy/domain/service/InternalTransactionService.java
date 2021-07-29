package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class InternalTransactionService {

  @Autowired
  private InternalTransactionDAO internalTransactionDAO;

  public List<InternalTransaction> getInternalTransactionsBySenderAccountId(final Long senderAccountId) {
    List<InternalTransaction> internalTransactions = internalTransactionDAO.findAllBySenderAccountId(senderAccountId);
    return internalTransactions;
  }

}

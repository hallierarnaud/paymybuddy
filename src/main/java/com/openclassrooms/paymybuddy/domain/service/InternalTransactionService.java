package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.Data;

@Data
@Service
public class InternalTransactionService {

  @Autowired
  private InternalTransactionDAO internalTransactionDAO;

  @Autowired
  private InternalAccountDAO internalAccountDAO;

  @Autowired
  private MapService mapService;

  public List<InternalTransaction> getInternalTransactionsBySenderAccountId(final Long senderAccountId) {
    List<InternalTransaction> internalTransactions = internalTransactionDAO.findAllBySenderAccountId(senderAccountId);
    return internalTransactions;
  }

  public InternalTransactionResponse addInternalTransaction(InternalTransactionRequest internalTransactionRequest) {
    InternalTransaction internalTransaction = new InternalTransaction();
    internalTransaction.setDescription(internalTransactionRequest.getDescription());
    internalTransaction.setTransferredAmount(internalTransactionRequest.getTransferredAmount());
    InternalAccount senderInternalAccount = internalAccountDAO.findById(internalTransactionRequest.getSenderInternalAccountId());
    senderInternalAccount.setBalance(senderInternalAccount.getBalance() - internalTransaction.getTransferredAmount());
    internalTransaction.setSenderInternalAccount(senderInternalAccount);
    InternalAccount recipientInternalAccount = internalAccountDAO.findById(internalTransactionRequest.getRecipientInternalAccountId());
    recipientInternalAccount.setBalance(recipientInternalAccount.getBalance() + internalTransaction.getTransferredAmount() * 0.995);
    internalTransaction.setRecipientInternalAccount(recipientInternalAccount);
    internalTransactionDAO.addInternalTransaction(internalTransaction);

    InternalTransactionResponse internalTransactionResponse = mapService.convertInternalTransactionToInternalTransactionResponse(internalTransaction);
    return internalTransactionResponse;
  }

}

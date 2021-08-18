package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

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
  private LoginDAO loginDAO;

  @Autowired
  private MapService mapService;

  public List<InternalTransaction> getInternalTransactionsBySenderAccountId(final Long senderAccountId) {
    List<InternalTransaction> internalTransactions = internalTransactionDAO.findAllBySenderAccountId(senderAccountId);
    for (InternalTransaction internalTransaction : internalTransactions) {
      internalTransaction.setRecipientInternalAccountEmail(loginDAO.findByUserId(internalTransaction.getRecipientInternalAccountId()).getEmail());
    }
    return internalTransactions;
  }

  public InternalTransactionResponse addInternalTransaction(InternalTransactionRequest internalTransactionRequest) {
    InternalTransaction internalTransaction = new InternalTransaction();
    InternalAccount senderInternalAccount = internalAccountDAO.findById(internalTransactionRequest.getSenderInternalAccountId());
    /**
     * check that the balance of the user's account is sufficient to perform the transaction
     */
    if (senderInternalAccount.getBalance() < internalTransactionRequest.getTransferredAmount()) {
      throw new IllegalArgumentException ("Sorry but your balance is less than the wished amount to transfer.");
    } else {
      internalTransaction.setDescription(internalTransactionRequest.getDescription());
      internalTransaction.setTransferredAmount(internalTransactionRequest.getTransferredAmount());
      /**
       * update the balance of the user's account resulting of the transaction
       */
      senderInternalAccount.setBalance(senderInternalAccount.getBalance() - internalTransaction.getTransferredAmount());
      internalTransaction.setSenderInternalAccount(senderInternalAccount);
      /**
       * update the balance of the recipient's account resulting of the transaction taking into account the fees
       */
      InternalAccount recipientInternalAccount = internalAccountDAO.findById(internalTransactionRequest.getRecipientInternalAccountId());
      recipientInternalAccount.setBalance(recipientInternalAccount.getBalance() + internalTransaction.getTransferredAmount() * 0.995);
      internalTransaction.setRecipientInternalAccount(recipientInternalAccount);
      internalTransactionDAO.addInternalTransaction(internalTransaction);
    }

    //InternalTransactionResponse internalTransactionResponse = mapService.convertInternalTransactionToInternalTransactionResponse(internalTransaction);
    InternalTransactionResponse internalTransactionResponse = new InternalTransactionResponse();
    internalTransactionResponse.setId(internalTransaction.getId());
    internalTransactionResponse.setDescription(internalTransaction.getDescription());
    internalTransactionResponse.setTransferredAmount(internalTransaction.getTransferredAmount());
    internalTransactionResponse.setSenderInternalAccountId(internalTransaction.getSenderInternalAccountId());
    internalTransactionResponse.setRecipientInternalAccountId(internalTransaction.getRecipientInternalAccountId());
    internalTransactionResponse.setRecipientInternalAccountEmail(internalTransaction.getRecipientInternalAccountEmail());
    return internalTransactionResponse;
  }

}

package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.model.DAO.ExternalAccountDAO;
import com.openclassrooms.paymybuddy.model.DAO.ExternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class ExternalTransactionService {

  @Autowired
  private ExternalTransactionDAO externalTransactionDAO;

  @Autowired
  private InternalAccountDAO internalAccountDAO;

  @Autowired
  private ExternalAccountDAO externalAccountDAO;

  public ExternalTransaction getExternalTransaction(final Long id) {
    if (externalTransactionDAO.findById(id) == null) {
      throw new NoSuchElementException("external transaction " + id + " doesn't exist");
    }
    return externalTransactionDAO.findById(id);
  }

  public ExternalTransactionResponse addExternalTransaction(ExternalTransactionRequest externalTransactionRequest) {
    ExternalTransaction externalTransaction = new ExternalTransaction();
    externalTransaction.setDescription(externalTransactionRequest.getDescription());
    externalTransaction.setTransferredAmount(externalTransactionRequest.getTransferredAmount());
    InternalAccount internalAccount = internalAccountDAO.findById(externalTransactionRequest.getInternalAccountId());
    /**
     * update the balance of the user's internal account resulting of a transaction from an external account taking into account the fees
     */
    if (externalTransaction.getTransferredAmount() > 0) {
      internalAccount.setBalance(internalAccount.getBalance() + externalTransaction.getTransferredAmount() * 0.995);
    } else {
      /**
       * check that the balance of the user's internal account is sufficient to perform a transaction to his external account
       */
      if (internalAccount.getBalance() < -externalTransactionRequest.getTransferredAmount()) {
        throw new IllegalArgumentException ("Sorry but your balance is less than the wished amount to transfer.");
      } else {
        /**
         * update the balance of the user's internal account resulting of a transaction to his external account taking into account the fees
         */
        internalAccount.setBalance(internalAccount.getBalance() + externalTransaction.getTransferredAmount() * 1.005);
      }
    }
    externalTransaction.setInternalAccount(internalAccount);
    ExternalAccount externalAccount = externalAccountDAO.findById(externalTransactionRequest.getExternalAccountId());
    externalTransaction.setExternalAccount(externalAccount);
    externalTransactionDAO.addExternalTransaction(externalTransaction);

    ExternalTransactionResponse externalTransactionResponse = new ExternalTransactionResponse();
    externalTransactionResponse.setId(externalTransaction.getId());
    externalTransactionResponse.setInternalAccountBalance(internalAccount.getBalance());
    return externalTransactionResponse;
  }

}

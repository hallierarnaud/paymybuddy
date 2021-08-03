package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.controller.DTO.UserAccountResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;

import org.springframework.stereotype.Service;

@Service
public class MapService {

  public UserAccountResponse convertUserAccountToUserAccountResponse(UserAccount userAccount) {
    UserAccountResponse userAccountResponse = new UserAccountResponse();
    userAccountResponse.setLoginId(userAccount.getLoginId());
    userAccountResponse.setEmail(userAccount.getEmail());
    userAccountResponse.setPassword(userAccount.getPassword());
    userAccountResponse.setUserId(userAccount.getUserId());
    userAccountResponse.setLastname(userAccount.getLastname());
    userAccountResponse.setFirstname(userAccount.getFirstname());
    userAccountResponse.setBirthdate(userAccount.getBirthdate());
    userAccountResponse.setInternalAccountId(userAccount.getInternalAccountId());
    userAccountResponse.setBalance(userAccount.getBalance());
    userAccountResponse.setExternalAccountId(userAccount.getExternalAccountId());
    userAccountResponse.setIban(userAccount.getIban());
    return userAccountResponse;
  }

  public ContactResponse convertContactToContactResponse(Contact contact) {
    ContactResponse contactResponse = new ContactResponse();
    contactResponse.setRelationId(contact.getRelationId());
    contactResponse.setUserId(contact.getUserId());
    contactResponse.setContactId(contact.getContactId());
    contactResponse.setContactEmail(contact.getContactEmail());
    return contactResponse;
  }

  public InternalTransactionResponse convertInternalTransactionToInternalTransactionResponse(InternalTransaction internalTransaction) {
    InternalTransactionResponse internalTransactionResponse = new InternalTransactionResponse();
    internalTransactionResponse.setId(internalTransaction.getId());
    internalTransactionResponse.setDescription(internalTransaction.getDescription());
    internalTransactionResponse.setTransferredAmount(internalTransaction.getTransferredAmount());
    internalTransactionResponse.setSenderInternalAccountId(internalTransaction.getSenderInternalAccount().getId());
    internalTransactionResponse.setRecipientInternalAccountId(internalTransaction.getRecipientInternalAccount().getId());
    return internalTransactionResponse;
  }

}

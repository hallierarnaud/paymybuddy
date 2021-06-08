package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.ExternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class MapDAO {

  public User updateUserWithUserEntity (User user, UserEntity userEntity) {
    user.setId(userEntity.getId());
    user.setLastname(userEntity.getLastname());
    user.setFirstname(userEntity.getFirstname());
    user.setBirthdate(userEntity.getBirthdate());
    return user;
  }

  public UserEntity updateUserEntityWithUser (UserEntity userEntity, User user) {
    userEntity.setLastname(user.getLastname());
    userEntity.setFirstname(user.getFirstname());
    userEntity.setBirthdate(user.getBirthdate());
    return userEntity;
  }

  public Login updateLoginWithLoginEntity (Login login, LoginEntity loginEntity) {
    login.setId(loginEntity.getId());
    login.setEmail(loginEntity.getEmail());
    login.setPassword(loginEntity.getPassword());
    return login;
  }

  public LoginEntity updateLoginEntityWithLogin (LoginEntity loginEntity, Login login) {
    loginEntity.setEmail(login.getEmail());
    loginEntity.setPassword(login.getPassword());
    return loginEntity;
  }

  public InternalTransaction updateInternalTransactionWithInternalTransactionEntity (InternalTransaction internalTransaction, InternalTransactionEntity internalTransactionEntity) {
    internalTransaction.setId(internalTransactionEntity.getId());
    internalTransaction.setDescription(internalTransactionEntity.getDescription());
    internalTransaction.setTransferredAmount(internalTransactionEntity.getTransferredAmount());
    return internalTransaction;
  }

  public InternalAccount updateInternalAccountWithInternalAccountEntity (InternalAccount internalAccount, InternalAccountEntity internalAccountEntity) {
    internalAccount.setId(internalAccountEntity.getId());
    internalAccount.setBalance(internalAccountEntity.getBalance());
    return internalAccount;
  }

  public InternalAccountEntity updateInternalAccountEntityWithInternalAccount(InternalAccountEntity internalAccountEntity, InternalAccount internalAccount) {
    internalAccountEntity.setId(internalAccount.getId());
    internalAccountEntity.setBalance(internalAccount.getBalance());
    return internalAccountEntity;
  }

  public ExternalTransaction updateExternalTransactionWithExternalTransactionEntity (ExternalTransaction externalTransaction, ExternalTransactionEntity externalTransactionEntity) {
    externalTransaction.setId(externalTransactionEntity.getId());
    externalTransaction.setDescription(externalTransactionEntity.getDescription());
    externalTransaction.setTransferredAmount(externalTransactionEntity.getTransferredAmount());
    return externalTransaction;
  }

  public ExternalAccount updateExternalAccountWithExternalAccountEntity (ExternalAccount externalAccount, ExternalAccountEntity externalAccountEntity) {
    externalAccount.setId(externalAccountEntity.getId());
    externalAccount.setIban(externalAccountEntity.getIban());
    return externalAccount;
  }

  public ExternalAccountEntity updateExternalAccountEntityWithExternalAccount(ExternalAccountEntity externalAccountEntity, ExternalAccount externalAccount) {
    externalAccountEntity.setId(externalAccount.getId());
    externalAccountEntity.setIban(externalAccount.getIban());
    return externalAccountEntity;
  }

  public Contact updateContactWithContactEntity (Contact contact, ContactEntity contactEntity) {
    contact.setId(contactEntity.getId());
    return contact;
  }

  public ContactEntity updateContactEntityWithContact(ContactEntity contactEntity, Contact contact) {
    contactEntity.setId(contactEntity.getId());
    return contactEntity;
  }

}

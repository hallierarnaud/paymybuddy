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
    userEntity.setId(user.getId());
    userEntity.setLastname(user.getLastname());
    userEntity.setFirstname(user.getFirstname());
    userEntity.setBirthdate(user.getBirthdate());
    return userEntity;
  }

  public Login updateLoginWithLoginEntity (Login login, LoginEntity loginEntity) {
    loginEntity.setId(login.getId());
    loginEntity.setEmail(login.getEmail());
    loginEntity.setPassword(login.getPassword());
    return login;
  }

  public InternalTransaction updateInternalTransactionWithInternalTransactionEntity (InternalTransaction internalTransaction, InternalTransactionEntity internalTransactionEntity) {
    internalTransactionEntity.setId(internalTransaction.getId());
    internalTransactionEntity.setDescription(internalTransaction.getDescription());
    internalTransactionEntity.setTransferredAmount(internalTransaction.getTransferredAmount());
    return internalTransaction;
  }

  public InternalAccount updateInternalAccountWithInternalAccountEntity (InternalAccount internalAccount, InternalAccountEntity internalAccountEntity) {
    internalAccountEntity.setId(internalAccount.getId());
    internalAccountEntity.setBalance(internalAccount.getBalance());
    return internalAccount;
  }

  public ExternalTransaction updateExternalTransactionWithExternalTransactionEntity (ExternalTransaction externalTransaction, ExternalTransactionEntity externalTransactionEntity) {
    externalTransactionEntity.setId(externalTransaction.getId());
    externalTransactionEntity.setDescription(externalTransaction.getDescription());
    externalTransactionEntity.setTransferredAmount(externalTransaction.getTransferredAmount());
    return externalTransaction;
  }

  public ExternalAccount updateExternalAccountWithExternalAccountEntity (ExternalAccount externalAccount, ExternalAccountEntity externalAccountEntity) {
    externalAccountEntity.setId(externalAccount.getId());
    externalAccountEntity.setIban(externalAccount.getIban());
    return externalAccount;
  }

  public Contact updateContactWithContactEntity (Contact contact, ContactEntity contactEntity) {
    contactEntity.setId(contact.getId());
    return contact;
  }

}

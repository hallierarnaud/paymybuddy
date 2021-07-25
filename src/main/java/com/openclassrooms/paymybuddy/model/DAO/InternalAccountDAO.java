package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class InternalAccountDAO {

  @Autowired
  private InternalAccountRepository internalAccountRepository;

  @Autowired
  MapDAO mapDAO;

  public InternalAccount findById(Long id) {
    InternalAccountEntity internalAccountEntity = internalAccountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("internal account " + id + " doesn't exist"));
    InternalAccount internalAccount = new InternalAccount();
    mapDAO.updateInternalAccountWithInternalAccountEntity(internalAccount, internalAccountEntity);
    return internalAccount;
  }

  public InternalAccount addInternalAccount(InternalAccount internalAccount) {
    InternalAccountEntity internalAccountEntity = new InternalAccountEntity();
    mapDAO.updateInternalAccountEntityWithInternalAccount(internalAccountEntity, internalAccount);
    internalAccountRepository.save(internalAccountEntity);
    return internalAccount;
  }

}

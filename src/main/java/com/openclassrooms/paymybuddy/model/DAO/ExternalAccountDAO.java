package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class ExternalAccountDAO {

  @Autowired
  private ExternalAccountRepository externalAccountRepository;

  @Autowired
  MapDAO mapDAO;

  public ExternalAccount findById(Long id) {
    ExternalAccountEntity externalAccountEntity = externalAccountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("external account " + id + " doesn't exist"));
    ExternalAccount externalAccount = new ExternalAccount();
    mapDAO.updateExternalAccountWithExternalAccountEntity(externalAccount, externalAccountEntity);
    return externalAccount;
  }

}

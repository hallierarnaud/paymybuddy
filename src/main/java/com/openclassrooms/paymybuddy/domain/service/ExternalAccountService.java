package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.model.DAO.ExternalAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class ExternalAccountService {

  @Autowired
  private ExternalAccountDAO externalAccountDAO;

  public ExternalAccount getExternalAccount(final Long id) {
    if (externalAccountDAO.findById(id) == null) {
      throw new NoSuchElementException("external account " + id + " doesn't exist");
    }
    return externalAccountDAO.findById(id);
  }

}

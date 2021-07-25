package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class InternalAccountService {

  @Autowired
  private InternalAccountDAO internalAccountDAO;

  public InternalAccount getInternalAccount(final Long id) {
    if (internalAccountDAO.findById(id) == null) {
      throw new NoSuchElementException("internal account " + id + " doesn't exist");
    }
    return internalAccountDAO.findById(id);
  }

}

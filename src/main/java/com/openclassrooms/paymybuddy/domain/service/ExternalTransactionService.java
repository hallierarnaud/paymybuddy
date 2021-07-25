package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.ExternalTransactionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class ExternalTransactionService {

  @Autowired
  private ExternalTransactionDAO externalTransactionDAO;

  public ExternalTransaction getExternalTransaction(final Long id) {
    if (externalTransactionDAO.findById(id) == null) {
      throw new NoSuchElementException("external transaction " + id + " doesn't exist");
    }
    return externalTransactionDAO.findById(id);
  }

}

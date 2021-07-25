package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import lombok.Data;

@Data
@Service
public class InternalTransactionService {

  @Autowired
  private InternalTransactionDAO internalTransactionDAO;

  public InternalTransaction getInternalTransaction(final Long id) {
    if (internalTransactionDAO.findById(id) == null) {
      throw new NoSuchElementException("internal transaction " + id + " doesn't exist");
    }
    return internalTransactionDAO.findById(id);
  }

}

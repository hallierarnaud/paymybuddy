package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.InternalTransactionService;
import com.openclassrooms.paymybuddy.domain.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
public class InternalTransactionController {

  @Autowired
  private InternalTransactionService internalTransactionService;

  @GetMapping("/internaltransactions/{id}")
  public InternalTransaction getInternalTransactionById(@PathVariable("id") long id) {
    try {
      return internalTransactionService.getInternalTransaction(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "internal transaction " + id + " doesn't exist");
    }
  }

}

package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.ExternalTransactionService;
import com.openclassrooms.paymybuddy.domain.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin(origins="*")
@RestController
public class ExternalTransactionController {

  @Autowired
  private ExternalTransactionService externalTransactionService;

  @GetMapping("/externaltransactions/{id}")
  public ExternalTransaction getExternalTransactionById(@PathVariable("id") long id) {
    try {
      return externalTransactionService.getExternalTransaction(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "external transaction " + id + " doesn't exist");
    }
  }

  @PostMapping("/externaltransactions")
  public ExternalTransactionResponse addExternalTransaction(@RequestBody ExternalTransactionRequest externalTransactionRequest) {
    return externalTransactionService.addExternalTransaction(externalTransactionRequest);
  }

}

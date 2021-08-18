package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.service.ExternalTransactionService;

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

/**
 * a class to perform CRUD operations on external transaction
 */
@CrossOrigin(origins="*")
@RestController
public class ExternalTransactionController {

  @Autowired
  private ExternalTransactionService externalTransactionService;

  /**
   * @param id an external transaction's id
   * @return an external transaction corresponding to the id
   */
  @GetMapping("/externaltransactions/{id}")
  public ExternalTransaction getExternalTransactionById(@PathVariable("id") long id) {
    try {
      return externalTransactionService.getExternalTransaction(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "external transaction " + id + " doesn't exist");
    }
  }

  /**
   * @param externalTransactionRequest an external transaction defined by his attributes
   * @return add the external transaction to the database
   */
  @PostMapping("/externaltransactions")
  public ExternalTransactionResponse addExternalTransaction(@RequestBody ExternalTransactionRequest externalTransactionRequest) {
    try {
      return externalTransactionService.addExternalTransaction(externalTransactionRequest);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Sorry but your balance is less than the wished amount to transfer.");
    }
  }

}

package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.service.InternalTransactionService;
import com.openclassrooms.paymybuddy.domain.service.MapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * a class to perform CRUD operations on internal transaction
 */
@CrossOrigin(origins="*")
@RestController
public class InternalTransactionController {

  @Autowired
  private InternalTransactionService internalTransactionService;

  @Autowired
  private MapService mapService;

  /**
   * @param id an internal transaction's id
   * @return an internal account corresponding to the id
   */
  @GetMapping("/internaltransactions/{id}")
  public List<InternalTransactionResponse> getInternalTransactionsBySenderAccountId(@PathVariable("id") long id) {
    return internalTransactionService.getInternalTransactionsBySenderAccountId(id).stream().map(p -> mapService.convertInternalTransactionToInternalTransactionResponse(p)).collect(Collectors.toList());
  }

  /**
   * @param internalTransactionRequest an internal account defined by his attributes
   * @return add the internal account to the database
   */
  @PostMapping("/internaltransactions")
  public InternalTransactionResponse addInternalTransaction(@RequestBody InternalTransactionRequest internalTransactionRequest) {
    try {
      return internalTransactionService.addInternalTransaction(internalTransactionRequest);
    } catch (IllegalArgumentException e ) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Sorry but your balance is less than the wished amount to transfer.");
    }
  }

}

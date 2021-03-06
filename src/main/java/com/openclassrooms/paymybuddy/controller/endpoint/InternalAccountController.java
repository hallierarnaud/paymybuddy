package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.service.InternalAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

/**
 * a class to perform CRUD operations on internal account
 */
@RestController
public class InternalAccountController {

  @Autowired
  private InternalAccountService internalAccountService;

  /**
   * @param id an internal account's id
   * @return an internal account corresponding to the id
   */
  @GetMapping("/internalaccounts/{id}")
  public InternalAccount getInternalAccountById(@PathVariable("id") long id) {
    try {
      return internalAccountService.getInternalAccount(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "internal account " + id + " doesn't exist");
    }
  }

}

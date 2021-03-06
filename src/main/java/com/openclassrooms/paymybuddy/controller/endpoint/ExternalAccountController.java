package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.service.ExternalAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

/**
 * a class to perform CRUD operations on external account
 */
@RestController
public class ExternalAccountController {

  @Autowired
  private ExternalAccountService externalAccountService;

  /**
   * @param id an external account's id
   * @return an external account corresponding to the id
   */
  @GetMapping("/externalaccounts/{id}")
  public ExternalAccount getExternalAccountById(@PathVariable("id") long id) {
    try {
      return externalAccountService.getExternalAccount(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "external account " + id + " doesn't exist");
    }
  }

}

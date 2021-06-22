package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountRequest;
import com.openclassrooms.paymybuddy.controller.DTO.UserAccountResponse;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.MapService;
import com.openclassrooms.paymybuddy.domain.service.UserAccountService;

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

import javax.persistence.EntityExistsException;

// ajout annotation pour contourner le contrôle de la faille de sécurité
@CrossOrigin(origins="*")
@RestController
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private MapService mapService;

  @GetMapping("/useraccounts/{email}")
  public UserAccountResponse getLUserAccountByEmail(@PathVariable("email") String email) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.getUserAccount(email));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + email + " doesn't exist");
    }
  }

  @PostMapping("/useraccounts")
  public UserAccountResponse addUserAccount(@RequestBody UserAccountRequest userAccountRequest) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.addUserAccount(userAccountRequest));
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email " + userAccountRequest.getEmail() + " already exists");
    }
  }

}

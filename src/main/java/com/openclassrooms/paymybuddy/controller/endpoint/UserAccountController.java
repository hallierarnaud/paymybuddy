package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountRequest;
import com.openclassrooms.paymybuddy.controller.DTO.UserAccountResponse;
import com.openclassrooms.paymybuddy.domain.service.MapService;
import com.openclassrooms.paymybuddy.domain.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;

@RestController
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private MapService mapService;

  @PostMapping("/useraccounts")
  public UserAccountResponse addUserAccount(@RequestBody UserAccountRequest userAccountRequest) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.addUserAccount(userAccountRequest));
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email " + userAccountRequest.getEmail() + " already exists");
    }
  }

}

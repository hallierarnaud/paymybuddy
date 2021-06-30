package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountResponse;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

// ajout annotation pour contourner le contrôle de la faille de sécurité
@CrossOrigin(origins="*")
@RestController
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private MapService mapService;

  @GetMapping("/useraccounts/{email}/{password}")
  public UserAccountResponse getUserAccountByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.getUserAccount(email, password));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + email + " doesn't exist or password is false");
    }
  }

  @GetMapping("/useraccounts")
  public List<UserAccountResponse> getUserAccounts() {
    return userAccountService.getUserAccounts().stream().map(p -> mapService.convertUserAccountToUserAccountResponse(p)).collect(Collectors.toList());
  }

  // Ajout de la méthode pour checker l'existence du login
  @PostMapping("/checkuseraccounts")
  public UserAccountResponse checkUserAccount(@RequestBody Login login) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.checkUserAccount(login));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + login.getEmail() + " doesn't exist or password is false");
    }
  }

  // ajout d'une méthode pour récupérer le userAccount correspondant au login
  @GetMapping("/getuseraccounts/{email}")
  public UserAccountResponse getUserAccountByLogin(@PathVariable("email") String email) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.getUserAccountByLogin(email));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + email + " doesn't exist or password is false");
    }
  }

  @PostMapping("/useraccounts")
  public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
    try {
      return userAccountService.addUserAccount(userAccount);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email " + userAccount.getEmail() + " already exists");
    }
  }

}

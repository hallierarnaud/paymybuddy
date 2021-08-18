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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

/**
 * a class to perform CRUD operations on user account
 */
@CrossOrigin(origins="*")
@RestController
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private MapService mapService;

  /**
   * @return a list of all user accounts in the database
   */
  @GetMapping("/useraccounts")
  public List<UserAccountResponse> getUserAccounts() {
    return userAccountService.getUserAccounts().stream().map(p -> mapService.convertUserAccountToUserAccountResponse(p)).collect(Collectors.toList());
  }

  /**
   * @param login a login defined by his attributes
   * @return the user account corresponding to the login
   */
  @PostMapping("/checkuseraccounts")
  public UserAccountResponse checkUserAccount(@RequestBody Login login) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.checkUserAccount(login));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + login.getEmail() + " doesn't exist or password is false");
    }
  }

  /**
   * @param email a user's email
   * @return the user account corresponding to the email
   */
  @GetMapping("/getuseraccounts")
  public UserAccountResponse getUserAccountByLogin(@RequestParam("email") String email) {
    try {
      return mapService.convertUserAccountToUserAccountResponse(userAccountService.getUserAccountByLogin(email));
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email " + email + " doesn't exist or password is false");
    }
  }

  /**
   * @param userAccount a user account defined by his attributes
   * @return add the user account to the database
   */
  @PostMapping("/useraccounts")
  public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
    try {
      return userAccountService.addUserAccount(userAccount);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email " + userAccount.getEmail() + " already exists");
    }
  }

}

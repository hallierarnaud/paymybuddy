package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.UserAccountResponse;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;

import org.springframework.stereotype.Service;

@Service
public class MapService {

  public UserAccountResponse convertUserAccountToUserAccountResponse(UserAccount userAccount) {
    UserAccountResponse userAccountResponse = new UserAccountResponse();
    userAccountResponse.setLoginId(userAccount.getLoginId());
    userAccountResponse.setEmail(userAccount.getEmail());
    userAccountResponse.setPassword(userAccount.getPassword());
    userAccountResponse.setUserId(userAccount.getUserId());
    userAccountResponse.setLastname(userAccount.getLastname());
    userAccountResponse.setFirstname(userAccount.getFirstname());
    userAccountResponse.setBirthdate(userAccount.getBirthdate());
    userAccountResponse.setInternalAccountId(userAccount.getInternalAccountId());
    userAccountResponse.setBalance(userAccount.getBalance());
    userAccountResponse.setExternalAccountId(userAccount.getExternalAccountId());
    userAccountResponse.setIban(userAccount.getIban());
    return userAccountResponse;
  }

}

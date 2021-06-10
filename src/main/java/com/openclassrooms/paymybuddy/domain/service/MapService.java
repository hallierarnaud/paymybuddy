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
    return userAccountResponse;
  }

}

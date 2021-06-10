package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDAO {

  @Autowired
  private LoginRepository loginRepository;

  public UserAccount addUserAccount (UserAccount userAccount) {
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setEmail(userAccount.getEmail());
    loginEntity.setPassword(userAccount.getPassword());
    loginRepository.save(loginEntity);
    userAccount.setLoginId(loginEntity.getId());
    return userAccount;
  }

}

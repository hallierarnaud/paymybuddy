package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDAO {

  @Autowired
  private LoginRepository loginRepository;

  @Autowired
  private UserRepository userRepository;

  public UserAccount addUserAccount (UserAccount userAccount) {
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setEmail(userAccount.getEmail());
    loginEntity.setPassword(userAccount.getPassword());
    loginRepository.save(loginEntity);

    UserEntity userEntity = new UserEntity();
    userEntity.setLastname(userAccount.getLastname());
    userEntity.setFirstname(userAccount.getFirstname());
    userEntity.setBirthdate(userAccount.getBirthdate());
    userEntity.setLoginEntity(loginEntity);
    userRepository.save(userEntity);

    userAccount.setLoginId(loginEntity.getId());
    userAccount.setUserId(userEntity.getId());
    return userAccount;
  }

}

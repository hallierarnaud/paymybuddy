package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAccountDAO {

  @Autowired
  private LoginRepository loginRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InternalAccountRepository internalAccountRepository;

  @Autowired
  private ExternalAccountRepository externalAccountRepository;

  @Transactional
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

    InternalAccountEntity internalAccountEntity = new InternalAccountEntity();
    internalAccountEntity.setBalance(userAccount.getBalance());
    internalAccountEntity.setUserEntity(userEntity);
    internalAccountRepository.save(internalAccountEntity);

    ExternalAccountEntity externalAccountEntity = new ExternalAccountEntity();
    externalAccountEntity.setIban(userAccount.getIban());
    externalAccountEntity.setUserEntity(userEntity);
    externalAccountRepository.save(externalAccountEntity);

    userAccount.setLoginId(loginEntity.getId());
    userAccount.setUserId(userEntity.getId());
    userAccount.setInternalAccountId(internalAccountEntity.getId());
    userAccount.setExternalAccountId(externalAccountEntity.getId());
    return userAccount;
  }

}

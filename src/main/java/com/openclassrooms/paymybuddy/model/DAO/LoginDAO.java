package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class LoginDAO {

  @Autowired
  private LoginRepository loginRepository;

  @Autowired
  MapDAO mapDAO;

  public Login findById(Long id) {
    LoginEntity loginEntity = loginRepository.findById(id).orElseThrow(() -> new NoSuchElementException("login " + id + " doesn't exist"));
    Login login = new Login();
    mapDAO.updateLoginWithLoginEntity(login, loginEntity);
    return login;
  }

  public Login findByUserId(Long userId) {
    LoginEntity loginEntity = loginRepository.findByUserEntity_Id(userId).orElseThrow(() -> new NoSuchElementException("login doesn't exist"));
    Login login = new Login();
    login.setId(loginEntity.getId());
    login.setEmail(loginEntity.getEmail());
    login.setPassword(loginEntity.getPassword());
    return login;
  }

  public Login addLogin(Login login) {
    LoginEntity loginEntity = new LoginEntity();
    mapDAO.updateLoginEntityWithLogin(loginEntity, login);
    loginRepository.save(loginEntity);
    return login;
  }

  public boolean existById(Long id) {
    return loginRepository.existsById(id);
  }

  public boolean existsByEmail(String email) { return loginRepository.existsByEmail(email); }

}

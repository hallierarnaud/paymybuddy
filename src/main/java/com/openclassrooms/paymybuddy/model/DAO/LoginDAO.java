package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

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

  public Login addLogin(Login login) {
    LoginEntity loginEntity = new LoginEntity();
    mapDAO.updateLoginEntityWithLogin(loginEntity, login);
    loginRepository.save(loginEntity);
    return login;
  }

}

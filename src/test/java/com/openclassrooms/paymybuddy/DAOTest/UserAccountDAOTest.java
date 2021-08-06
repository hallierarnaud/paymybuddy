package com.openclassrooms.paymybuddy.DAOTest;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.MapDAO;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.LoginRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountDAOTest {

  @Mock
  private LoginRepository loginRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private InternalAccountRepository internalAccountRepository;

  @Mock
  private ExternalAccountRepository externalAccountRepository;

  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private UserAccountDAO userAccountDAO;

  @Test
  public void findByEmailAndPassword_shouldReturnOk() {
    // GIVEN
    LoginEntity loginEntity = new LoginEntity();
    Login login = new Login();
    login.setEmail("test@email.fr");
    login.setPassword("testPassword");
    UserAccount userAccount = new UserAccount();
    when(loginRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(java.util.Optional.of(loginEntity));

    // WHEN
    UserAccount actualUserAccount = userAccountDAO.findByEmailAndPassword(login);

    // THEN
    assertEquals(userAccount, actualUserAccount);
    verify(loginRepository).findByEmailAndPassword(anyString(), anyString());
  }

  @Test
  public void findByEmailAndPassword_shouldReturnNotFound() {
    // GIVEN
    Login login = new Login();
    login.setEmail("test@email.fr");
    login.setPassword("testPassword");

    // THEN
    assertThrows(NoSuchElementException.class, () -> userAccountDAO.findByEmailAndPassword(login));
  }

  @Test
  public void findByEmail_shouldReturnOk() {
    // GIVEN
    LoginEntity loginEntity = new LoginEntity();
    String email = "test@email.fr";
    UserAccount userAccount = new UserAccount();
    when(loginRepository.findByEmail(anyString())).thenReturn(java.util.Optional.of(loginEntity));

    // WHEN
    UserAccount actualUserAccount = userAccountDAO.findByEmail(email);

    // THEN
    assertEquals(userAccount, actualUserAccount);
    verify(loginRepository).findByEmail(anyString());
  }

  @Test
  public void findByEmail_shouldReturnNotFound() {
    // GIVEN
    String email = "test@email.fr";

    // THEN
    assertThrows(NoSuchElementException.class, () -> userAccountDAO.findByEmail(email));
  }

  @Test
  public void findAll_shouldReturnOk() {
    // GIVEN
    List<LoginEntity> loginEntities = new ArrayList<>();
    loginEntities.add(new LoginEntity());
    List<UserAccount> userAccounts = new ArrayList<>();
    userAccounts.add(new UserAccount());
    when(loginRepository.findAll()).thenReturn(loginEntities);

    // WHEN
    List<UserAccount> actualUserAccounts = userAccountDAO.findAll();

    // THEN
    assertEquals(userAccounts, actualUserAccounts);
    verify(loginRepository).findAll();
  }

  @Test
  public void addUserAccount_shouldReturnOK() {
    // GIVEN
    LoginEntity loginEntity = new LoginEntity();
    UserEntity userEntity = new UserEntity();
    InternalAccountEntity internalAccountEntity = new InternalAccountEntity();
    ExternalAccountEntity externalAccountEntity = new ExternalAccountEntity();
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");
    when(loginRepository.save(any(LoginEntity.class))).thenReturn(loginEntity);
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
    when(internalAccountRepository.save(any(InternalAccountEntity.class))).thenReturn(internalAccountEntity);
    when(externalAccountRepository.save(any(ExternalAccountEntity.class))).thenReturn(externalAccountEntity);

    // WHEN
    UserAccount addedUserAccount = userAccountDAO.addUserAccount(userAccount);

    // THEN
    assertEquals("test@email.fr", addedUserAccount.getEmail());
    verify(loginRepository).save(any(LoginEntity.class));
    verify(userRepository).save(any(UserEntity.class));
    verify(internalAccountRepository).save(any(InternalAccountEntity.class));
    verify(externalAccountRepository).save(any(ExternalAccountEntity.class));
  }

}

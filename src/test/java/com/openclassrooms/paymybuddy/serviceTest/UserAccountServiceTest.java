package com.openclassrooms.paymybuddy.serviceTest;

import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.domain.service.UserAccountService;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;
import com.openclassrooms.paymybuddy.model.DAO.UserAccountDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

  @Mock
  UserAccountDAO userAccountDAO;

  @Mock
  LoginDAO loginDAO;

  @InjectMocks
  private UserAccountService userAccountService;

  @Test
  public void checkUserAccount_shouldReturnOk () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");
    Login login = new Login();
    when(userAccountDAO.findByEmailAndPassword(any(Login.class))).thenReturn(userAccount);

    // WHEN
    UserAccount actualUserAccount = userAccountService.checkUserAccount(login);

    // THEN
    assertEquals("test@email.fr", actualUserAccount.getEmail());
    verify(userAccountDAO, times(2)).findByEmailAndPassword(any(Login.class));
  }

  @Test
  public void checkUserAccount_shouldReturnNotFound () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");
    Login login = new Login();

    // WHEN
    when(userAccountDAO.findByEmailAndPassword(any(Login.class))).thenReturn(null);

    // THEN
    assertThrows(NoSuchElementException.class, () -> userAccountService.checkUserAccount(login));
    verify(userAccountDAO).findByEmailAndPassword(any(Login.class));
  }

  @Test
  public void getUserAccountByLogin_shouldReturnOk () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");
    when(userAccountDAO.findByEmail(anyString())).thenReturn(userAccount);

    // WHEN
    UserAccount actualUserAccount = userAccountService.getUserAccountByLogin(userAccount.getEmail());

    // THEN
    assertEquals("test@email.fr", actualUserAccount.getEmail());
    verify(userAccountDAO, times(2)).findByEmail(anyString());
  }

  @Test
  public void getUserAccountByLogin_shouldReturnNotFound () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");

    // WHEN
    when(userAccountDAO.findByEmail(anyString())).thenReturn(null);

    // THEN
    assertThrows(NoSuchElementException.class, () -> userAccountService.getUserAccountByLogin(userAccount.getEmail()));
    verify(userAccountDAO).findByEmail(anyString());
  }

  @Test
  public void getUserAccounts_shouldReturnOk () {
    // GIVEN
    List<UserAccount> userAccounts = new ArrayList<>();
    userAccounts.add(new UserAccount());
    when(userAccountDAO.findAll()).thenReturn(userAccounts);

    // WHEN
    List<UserAccount> actualUserAccounts = userAccountService.getUserAccounts();

    // THEN
    assertEquals(userAccounts, actualUserAccounts);
    verify(userAccountDAO).findAll();
  }

  @Test
  public void addUserAccount_shouldReturnOk () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");
    when(userAccountDAO.addUserAccount(any(UserAccount.class))).thenReturn(userAccount);

    // WHEN
    UserAccount addedUserAccount = userAccountService.addUserAccount(userAccount);

    // THEN
    assertEquals("test@email.fr", addedUserAccount.getEmail());
    verify(userAccountDAO).addUserAccount(any(UserAccount.class));
  }

  @Test
  public void addUserAccount_shouldReturnAlreadyExist () {
    // GIVEN
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("test@email.fr");

    // WHEN
    when(loginDAO.existsByEmail(anyString())).thenReturn(TRUE);

    // THEN
    Throwable exception = assertThrows(EntityExistsException.class, () -> userAccountService.addUserAccount(userAccount));
    assertEquals("email test@email.fr already exists", exception.getMessage());
  }

}

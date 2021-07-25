package com.openclassrooms.paymybuddy.serviceTest;

import com.openclassrooms.paymybuddy.controller.DTO.UserRequest;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.domain.service.UserService;
import com.openclassrooms.paymybuddy.model.DAO.UserDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserDAO userDAO;

  @InjectMocks
  private UserService userService;

  @Test
  public void getUser_shouldReturnOk () {
    // GIVEN
    User user = new User();
    user.setId(1L);
    user.setFirstname("Homer");
    when(userDAO.findById(anyLong())).thenReturn(user);

    // WHEN
    User actualUser = userService.getUser(user.getId());

    // THEN
    assertEquals("Homer", actualUser.getFirstname());
    verify(userDAO, times(2)).findById(user.getId());
  }

  @Test
  public void getUser_shouldReturnNotFound () {
    // GIVEN
    User user = new User();
    user.setId(1L);

    // WHEN
    when(userDAO.findById(anyLong())).thenReturn(null);

    // THEN
    assertThrows(NoSuchElementException.class, () -> userService.getUser(user.getId()));
    verify(userDAO).findById(anyLong());
  }

  @Test
  public void getUsers_shouldReturnOk () {
    // GIVEN
    List<User> users = new ArrayList<>();
    users.add(new User());
    when(userDAO.findAll()).thenReturn(users);

    // WHEN
    List<User> actualUsers = userService.getUsers();

    // THEN
    assertEquals(users, actualUsers);
    verify(userDAO).findAll();
  }

  @Test
  public void updateUser_shouldReturnOk () {
    // GIVEN
    UserRequest userRequest = new UserRequest();
    userRequest.setFirstname("Homer");
    User user = new User();
    user.setId(1L);
    when(userDAO.findById(anyLong())).thenReturn(user);
    when(userDAO.updateUser(anyLong(), any(User.class))).thenReturn(user);

    // WHEN
    User updatedUser = userService.updateUser(user.getId(), userRequest);

    // THEN
    assertEquals(user.getFirstname(), updatedUser.getFirstname());
    verify(userDAO, times(2)).findById(anyLong());
    verify(userDAO).updateUser(user.getId(), user);
  }

  @Test
  public void updateUser_shouldReturnNotFound () {
    // GIVEN
    User user = new User();
    user.setId(1L);
    UserRequest userRequest = new UserRequest();

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> userService.updateUser(user.getId(), userRequest));
    assertEquals("user 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addUser_shouldReturnOk () {
    // GIVEN
    UserRequest userRequest = new UserRequest();
    userRequest.setFirstname("Homer");
    User user = new User();
    when(userDAO.addUser(any(User.class))).thenReturn(user);

    // WHEN
    User addedUser = userService.addUser(userRequest);

    // THEN
    assertEquals(user.getFirstname(), addedUser.getFirstname());
    verify(userDAO).addUser(any(User.class));
  }

  @Test
  public void addUser_shouldReturnAlreadyExist () {
    // GIVEN
    UserRequest userRequest = new UserRequest();
    userRequest.setId(1L);

    // WHEN
    when(userDAO.existById(anyLong())).thenReturn(TRUE);

    // THEN
    Throwable exception = assertThrows(EntityExistsException.class, () -> userService.addUser(userRequest));
    assertEquals("user 1 already exists", exception.getMessage());
  }

  @Test
  public void deleteUser_shouldReturnOk () {
    // GIVEN
    User user = new User();
    user.setId(1L);
    when(userDAO.existById(anyLong())).thenReturn(TRUE);

    // WHEN
    userService.deleteUser(user.getId());

    // THEN
    verify(userDAO).deleteUserById(user.getId());
  }

  @Test
  public void deleteUser_shouldReturnNotFound () {
    // GIVEN
    User user = new User();
    user.setId(1L);

    // WHEN
    when(userDAO.existById(anyLong())).thenReturn(FALSE);

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> userService.deleteUser(user.getId()));
    assertEquals("user 1 doesn't exist", exception.getMessage());
  }

}

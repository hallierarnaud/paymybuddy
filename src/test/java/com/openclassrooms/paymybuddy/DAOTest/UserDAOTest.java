package com.openclassrooms.paymybuddy.DAOTest;

import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.model.DAO.MapDAO;
import com.openclassrooms.paymybuddy.model.DAO.UserDAO;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private UserDAO userDAO;

  @Test
  public void findUserById_shouldReturnOk() {
    // GIVEN
    UserEntity userEntity = new UserEntity();
    User user = new User();
    when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(userEntity));

    // WHEN
    User actualUser = userDAO.findById(anyLong());

    // THEN
    assertEquals(user, actualUser);
    verify(userRepository).findById(anyLong());
  }

  @Test
  public void findUserById_shouldReturnNotFound() {
    // GIVEN
    User user = new User();
    user.setId(1L);

    // THEN
    assertThrows(NoSuchElementException.class, () -> userDAO.findById(user.getId()));
  }

  @Test
  public void findAllUsers_shouldReturnOk() {
    // GIVEN
    List<UserEntity> userEntities = new ArrayList<>();
    userEntities.add(new UserEntity());
    List<User> users = new ArrayList<>();
    users.add(new User());
    when(userRepository.findAll()).thenReturn(userEntities);

    // WHEN
    List<User> actualUsers = userDAO.findAll();

    // THEN
    assertEquals(users, actualUsers);
    verify(userRepository).findAll();
  }

  @Test
  public void updateUser_shouldReturnOK() {
    // GIVEN
    UserEntity userEntity = new UserEntity();
    User user = new User();
    user.setId(1L);
    user.setFirstname("Homer");
    when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(userEntity));
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    // WHEN
    User updatedUser = userDAO.updateUser(1l, user);

    // THEN
    assertEquals("Homer", updatedUser.getFirstname());
    verify(userRepository).findById(anyLong());
    verify(userRepository).save(any(UserEntity.class));
  }

  @Test
  public void updateUser_shouldReturnNotFound() {
    // GIVEN
    User user = new User();
    user.setId(1L);

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> userDAO.updateUser(user.getId(), user));
    assertEquals("user 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addUser_shouldReturnOK() {
    // GIVEN
    UserEntity userEntity = new UserEntity();
    User user = new User();
    user.setId(1L);
    user.setFirstname("Homer");
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    // WHEN
    User addedUser = userDAO.addUser(user);

    // THEN
    assertEquals("Homer", addedUser.getFirstname());
    verify(userRepository).save(any(UserEntity.class));
  }

  @Test
  public void deleteUserById_shouldReturnOk() {
    // GIVEN
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);

    // WHEN
    userRepository.deleteById(userEntity.getId());

    // THEN
    verify(userRepository).deleteById(anyLong());
  }

  @Test
  public void userExistById_shouldReturnOk() {
    // GIVEN
    when(userRepository.existsById(anyLong())).thenReturn(true);

    // WHEN
    Boolean actual = userDAO.existById(anyLong());

    // THEN
    assertEquals(true, actual);
    verify(userRepository).existsById(anyLong());
  }

}

package com.openclassrooms.paymybuddy.controllerTest;

import com.openclassrooms.paymybuddy.controller.endpoint.UserController;
import com.openclassrooms.paymybuddy.domain.object.User;
import com.openclassrooms.paymybuddy.domain.service.LoginService;
import com.openclassrooms.paymybuddy.domain.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void getUserById_shouldReturnOk() throws Exception {
    when(userService.getUser(any())).thenReturn(new User());
    mockMvc.perform(get("/users/1")).andExpect(status().isOk());
  }

  @Test
  public void getUserById_shouldReturnNotFound() throws Exception {
    when(userService.getUser(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(get("/users/1")).andExpect(status().isNotFound());
  }

  @Test
  public void getUsers_shouldReturnOk() throws Exception {
    when(userService.getUser(any())).thenReturn(new User());
    mockMvc.perform(get("/users")).andExpect(status().isOk());
  }

  @Test
  public void updateUser_shouldReturnOk() throws Exception {
    when(userService.updateUser(any(), any())).thenReturn(new User());
    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void updateUser_shouldReturnUnprocessableEntity() throws Exception {
    when(userService.updateUser(any(), any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void postUser_shouldReturnOk() throws Exception {
    when(userService.addUser(any())).thenReturn(new User());
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void postUser_shouldReturnUnprocessableEntity() throws Exception {
    when(userService.addUser(any())).thenThrow(EntityExistsException.class);
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void deleteUser_shouldReturnOk() throws Exception {
    doNothing().when(userService).deleteUser(any());
    mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
  }

  @Test
  public void deleteUser_shouldReturnNotFound() throws Exception {
    doThrow(NoSuchElementException.class).when(userService).deleteUser(any());
    mockMvc.perform(delete("/users/1")).andExpect(status().isNotFound());
  }

}

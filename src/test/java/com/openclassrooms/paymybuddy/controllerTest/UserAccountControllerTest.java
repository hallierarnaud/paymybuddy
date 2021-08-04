package com.openclassrooms.paymybuddy.controllerTest;

import com.openclassrooms.paymybuddy.controller.endpoint.UserAccountController;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.domain.service.MapService;
import com.openclassrooms.paymybuddy.domain.service.UserAccountService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserAccountController.class)
public class UserAccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserAccountService userAccountService;

  @MockBean
  private MapService mapService;

  @Test
  public void getUserAccountByLogin_shouldReturnOk() throws Exception {
    when(userAccountService.getUserAccountByLogin(any())).thenReturn(new UserAccount());
    mockMvc.perform(get("/getuseraccounts")
            .param("email", "test@email.fr"))
            .andExpect(status().isOk());
  }

  @Test
  public void getUserAccountByLogin_shouldReturnNotFound() throws Exception {
    when(userAccountService.getUserAccountByLogin(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(get("/getuseraccounts")
            .param("email", "test@email.fr"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void getUserAccounts_shouldReturnOk() throws Exception {
    when(userAccountService.getUserAccounts()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/useraccounts")).andExpect(status().isOk());
  }

  @Test
  public void checkUserAccount_shouldReturnOk() throws Exception {
    when(userAccountService.checkUserAccount(any())).thenReturn(new UserAccount());
    mockMvc.perform(post("/checkuseraccounts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void checkUserAccount_shouldReturnNotFound() throws Exception {
    when(userAccountService.checkUserAccount(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(post("/checkuseraccounts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void addUserAccount_shouldReturnOk() throws Exception {
    when(userAccountService.addUserAccount(any())).thenReturn(new UserAccount());
    mockMvc.perform(post("/useraccounts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void addUserAccount_shouldReturnUnprocessableEntity() throws Exception {
    when(userAccountService.addUserAccount(any())).thenThrow(EntityExistsException.class);
    mockMvc.perform(post("/useraccounts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

}

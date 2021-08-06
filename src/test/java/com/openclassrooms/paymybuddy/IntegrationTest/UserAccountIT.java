package com.openclassrooms.paymybuddy.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetUserAccount_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/getuseraccounts")
            .param("email", "hsimpson@email.fr"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstname", is("Homer")));
  }

  @Test
  public void testGetUserAccount_shouldReturnNotFound() throws Exception {
    mockMvc.perform(get("/getuseraccounts")
            .param("email", "test@email.fr"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testAddUser_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(post("/useraccounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"hsimpson@email.fr\",\"password\":\"testPassword\",\"lastname\":\"Kent\",\"firstname\":\"Clark\",\"birthdate\":\"1900-01-01\",\"balance\":\"0.00\",\"iban\":\"000\"}"))
            .andExpect(status().isUnprocessableEntity());
  }

}

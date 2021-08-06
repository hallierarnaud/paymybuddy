package com.openclassrooms.paymybuddy.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetLoginById_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/logins/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is("msimpson@email.fr")));
  }

  @Test
  public void testGetLoginById_shouldReturnNotFound() throws Exception {
    mockMvc.perform(get("/logins/1"))
            .andExpect(status().isNotFound());
  }

}

package com.openclassrooms.paymybuddy.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetUser_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstname", is("Homer")));
  }

  @Test
  public void testGetUser_shouldReturnNotFound() throws Exception {
    mockMvc.perform(get("/users/100"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testGetUsers_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstname", is("Homer")));
  }

  /*@Test
  public void testAddUser_shouldReturnOk() throws Exception {
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"100\", \"lastname\":\"Simpson\",\"firstname\":\"Bart\",\"birthdate\":\"2000-01-01\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstname", is("Bart")));
  }*/

  @Test
  public void testAddUser_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"1\", \"lastname\":\"Simpson\",\"firstname\":\"Bart\",\"birthdate\":\"2000-01-01\"}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testUpdateUser_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/users/1"))
            .andExpect(jsonPath("$.firstname", is("Homer")));
    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"1\", \"lastname\":\"Simpson\",\"firstname\":\"Bart\",\"birthdate\":\"1970-01-01\"}"))
            .andExpect(status().isOk());
    mockMvc.perform(get("/users/1"))
            .andExpect(jsonPath("$.firstname", is("Bart")));
    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"1\", \"lastname\":\"Simpson\",\"firstname\":\"Homer\",\"birthdate\":\"1970-01-01\"}"))
            .andExpect(status().isOk());
  }

  @Test
  public void testUpdateUser_shouldReturnNotFound() throws Exception {
    mockMvc.perform(put("/users/100")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testDeleteUser_shouldReturnOk() throws Exception {
    mockMvc.perform(delete("/users/100"));
    mockMvc.perform(get("/users/100"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testDeleteUser_shouldReturnNotFound() throws Exception {
    mockMvc.perform(delete("/persons/100"))
            .andExpect(status().isNotFound());
  }

}

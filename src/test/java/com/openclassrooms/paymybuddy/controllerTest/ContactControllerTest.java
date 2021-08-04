package com.openclassrooms.paymybuddy.controllerTest;

import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.controller.endpoint.ContactController;
import com.openclassrooms.paymybuddy.domain.service.ContactService;
import com.openclassrooms.paymybuddy.domain.service.MapService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import javax.persistence.EntityExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ContactService contactService;

  @MockBean
  private MapService mapService;

  @Test
  public void getContactsByUserId_shouldReturnOk() throws Exception {
    when(contactService.getContactsByUserId(any())).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/contacts")
            .param("userId", "1"))
            .andExpect(status().isOk());
  }

  @Test
  public void addContact_shouldReturnOk() throws Exception {
    when(contactService.addContact(any())).thenReturn(new ContactResponse());
    mockMvc.perform(post("/contacts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void addContact_shouldReturnUnprocessableEntity() throws Exception {
    when(contactService.addContact(any())).thenThrow(EntityExistsException.class);
    mockMvc.perform(post("/contacts")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

}

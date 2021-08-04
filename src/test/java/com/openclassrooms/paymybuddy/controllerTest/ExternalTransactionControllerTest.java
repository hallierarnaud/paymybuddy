package com.openclassrooms.paymybuddy.controllerTest;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.controller.endpoint.ExternalTransactionController;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.service.ExternalTransactionService;
import com.openclassrooms.paymybuddy.domain.service.MapService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExternalTransactionController.class)
public class ExternalTransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ExternalTransactionService externalTransactionService;

  @MockBean
  private MapService mapService;

  @Test
  public void getExternalTransactionById_shouldReturnOk() throws Exception {
    when(externalTransactionService.getExternalTransaction(any())).thenReturn(new ExternalTransaction());
    mockMvc.perform(get("/externaltransactions/1")).andExpect(status().isOk());
  }

  @Test
  public void getExternalTransactionById_shouldReturnNotFound() throws Exception {
    when(externalTransactionService.getExternalTransaction(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(get("/externaltransactions/1")).andExpect(status().isNotFound());
  }

  @Test
  public void addExternalTransaction_shouldReturnOk() throws Exception {
    when(externalTransactionService.addExternalTransaction(any())).thenReturn(new ExternalTransactionResponse());
    mockMvc.perform(post("/externaltransactions")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void addExternalTransaction_shouldReturnUnprocessableEntity() throws Exception {
    when(externalTransactionService.addExternalTransaction(any())).thenThrow(IllegalArgumentException.class);
    mockMvc.perform(post("/externaltransactions")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }
}

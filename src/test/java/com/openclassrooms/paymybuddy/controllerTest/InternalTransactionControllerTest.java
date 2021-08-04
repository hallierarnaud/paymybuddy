package com.openclassrooms.paymybuddy.controllerTest;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.controller.endpoint.InternalTransactionController;
import com.openclassrooms.paymybuddy.domain.service.InternalTransactionService;
import com.openclassrooms.paymybuddy.domain.service.MapService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InternalTransactionController.class)
public class InternalTransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private InternalTransactionService internalTransactionService;

  @MockBean
  private MapService mapService;

  @Test
  public void getInternalTransactionsBySenderAccountId_shouldReturnOk() throws Exception {
    when(internalTransactionService.getInternalTransactionsBySenderAccountId(any())).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/internaltransactions/1")).andExpect(status().isOk());
  }

  @Test
  public void addInternalTransaction_shouldReturnOk() throws Exception {
    when(internalTransactionService.addInternalTransaction(any())).thenReturn(new InternalTransactionResponse());
    mockMvc.perform(post("/internaltransactions")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void addInternalTransaction_shouldReturnUnprocessableEntity() throws Exception {
    when(internalTransactionService.addInternalTransaction(any())).thenThrow(IllegalArgumentException.class);
    mockMvc.perform(post("/internaltransactions")
            .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

}

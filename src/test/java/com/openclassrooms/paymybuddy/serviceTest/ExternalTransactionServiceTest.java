package com.openclassrooms.paymybuddy.serviceTest;

import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ExternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.service.ExternalTransactionService;
import com.openclassrooms.paymybuddy.model.DAO.ExternalAccountDAO;
import com.openclassrooms.paymybuddy.model.DAO.ExternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalTransactionServiceTest {

  @Mock
  private ExternalTransactionDAO externalTransactionDAO;

  @Mock
  private InternalAccountDAO internalAccountDAO;

  @Mock
  private ExternalAccountDAO externalAccountDAO;

  @InjectMocks
  private ExternalTransactionService externalTransactionService;

  @Test
  public void getExternalTransaction_shouldReturnOk () {
    // GIVEN
    ExternalTransaction externalTransaction = new ExternalTransaction();
    externalTransaction.setId(1L);
    externalTransaction.setDescription("testDescription");
    when(externalTransactionDAO.findById(anyLong())).thenReturn(externalTransaction);

    // WHEN
    ExternalTransaction actualExternalTransaction = externalTransactionService.getExternalTransaction(externalTransaction.getId());

    // THEN
    assertEquals("testDescription", actualExternalTransaction.getDescription());
    verify(externalTransactionDAO, times(2)).findById(externalTransaction.getId());
  }

  @Test
  public void getExternalTransaction_shouldReturnNotFound () {
    // GIVEN
    ExternalTransaction externalTransaction = new ExternalTransaction();
    externalTransaction.setId(1L);

    // WHEN
    when(externalTransactionDAO.findById(anyLong())).thenReturn(null);

    // THEN
    assertThrows(NoSuchElementException.class, () -> externalTransactionService.getExternalTransaction(externalTransaction.getId()));
    verify(externalTransactionDAO).findById(anyLong());
  }

  @Test
  public void addExternalTransaction_shouldReturnOk () {
    // GIVEN
    ExternalTransactionRequest externalTransactionRequest = new ExternalTransactionRequest();
    externalTransactionRequest.setDescription("testDescription");
    externalTransactionRequest.setTransferredAmount(5.00);
    externalTransactionRequest.setInternalAccountId(1L);
    ExternalTransaction externalTransaction = new ExternalTransaction();
    InternalAccount internalAccount = new InternalAccount();
    internalAccount.setBalance(10.00);
    when(internalAccountDAO.findById(externalTransactionRequest.getInternalAccountId())).thenReturn(internalAccount);
    externalTransaction.setInternalAccount(internalAccount);
    ExternalAccount externalAccount = new ExternalAccount();
    externalAccount.setIban("testIBAN");
    when(externalAccountDAO.findById(externalTransactionRequest.getExternalAccountId())).thenReturn(externalAccount);
    externalTransaction.setExternalAccount(externalAccount);

    // WHEN
    ExternalTransactionResponse addedExternalTransaction = externalTransactionService.addExternalTransaction(externalTransactionRequest);

    // THEN
    assertEquals(14.975, addedExternalTransaction.getInternalAccountBalance());
    verify(externalTransactionDAO).addExternalTransaction(any(ExternalTransaction.class));
  }

}

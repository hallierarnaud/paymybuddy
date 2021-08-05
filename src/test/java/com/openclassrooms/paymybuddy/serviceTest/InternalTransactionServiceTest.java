package com.openclassrooms.paymybuddy.serviceTest;

import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionRequest;
import com.openclassrooms.paymybuddy.controller.DTO.InternalTransactionResponse;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.InternalTransactionService;
import com.openclassrooms.paymybuddy.model.DAO.InternalAccountDAO;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InternalTransactionServiceTest {

  @Mock
  private InternalTransactionDAO internalTransactionDAO;

  @Mock
  private LoginDAO loginDAO;

  @Mock
  private InternalAccountDAO internalAccountDAO;

  @InjectMocks
  private InternalTransactionService internalTransactionService;

  @Test
  public void getInternalTransactionsBySenderAccountId_shouldReturnOk () {
    // GIVEN
    List<InternalTransaction> internalTransactions = new ArrayList<>();
    InternalTransaction internalTransaction = new InternalTransaction();
    internalTransaction.setDescription("testDescription");
    internalTransaction.setRecipientInternalAccountId(1L);
    internalTransactions.add(internalTransaction);
    Login login = new Login();
    login.setEmail("test@email.fr");
    when(internalTransactionDAO.findAllBySenderAccountId(anyLong())).thenReturn(internalTransactions);
    when(loginDAO.findByUserId(anyLong())).thenReturn(login);

    // WHEN
    List<InternalTransaction> actualInternalTransactions = internalTransactionService.getInternalTransactionsBySenderAccountId(1L);

    // THEN
    assertEquals("testDescription", actualInternalTransactions.get(0).getDescription());
    assertEquals("test@email.fr", actualInternalTransactions.get(0).getRecipientInternalAccountEmail());
    verify(internalTransactionDAO).findAllBySenderAccountId(anyLong());
  }

  @Test
  public void addInternalTransaction_shouldReturnOk () {
    // GIVEN
    InternalTransactionRequest internalTransactionRequest = new InternalTransactionRequest();
    internalTransactionRequest.setDescription("testDescription");
    internalTransactionRequest.setTransferredAmount(5.00);
    internalTransactionRequest.setSenderInternalAccountId(1L);
    InternalTransaction internalTransaction = new InternalTransaction();
    InternalAccount senderInternalAccount = new InternalAccount();
    senderInternalAccount.setBalance(10.00);
    when(internalAccountDAO.findById(internalTransactionRequest.getSenderInternalAccountId())).thenReturn(senderInternalAccount);
    internalTransaction.setSenderInternalAccount(senderInternalAccount);
    InternalAccount recipientInternalAccount = new InternalAccount();
    recipientInternalAccount.setBalance(100.00);
    when(internalAccountDAO.findById(internalTransactionRequest.getRecipientInternalAccountId())).thenReturn(recipientInternalAccount);
    internalTransaction.setRecipientInternalAccount(recipientInternalAccount);

    // WHEN
    InternalTransactionResponse addedInternalTransaction = internalTransactionService.addInternalTransaction(internalTransactionRequest);

    // THEN
    assertEquals("testDescription", addedInternalTransaction.getDescription());
    assertEquals(5.0, senderInternalAccount.getBalance());
    assertEquals(104.975, recipientInternalAccount.getBalance());
    verify(internalTransactionDAO).addInternalTransaction(any(InternalTransaction.class));
  }

}

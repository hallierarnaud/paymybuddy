package com.openclassrooms.paymybuddy.DAOTest;

import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.domain.object.InternalTransaction;
import com.openclassrooms.paymybuddy.model.DAO.InternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.MapDAO;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalTransactionRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InternalTransactionDAOTest {

  @Mock
  private InternalTransactionRepository internalTransactionRepository;

  @Mock
  private InternalAccountRepository internalAccountRepository;

  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private InternalTransactionDAO internalTransactionDAO;

  @Test
  public void findAllBySenderAccountId_shouldReturnOk() {
    // GIVEN
    List<InternalTransactionEntity> internalTransactionEntities = new ArrayList<>();
    internalTransactionEntities.add(new InternalTransactionEntity());
    List<InternalTransaction> internalTransactions = new ArrayList<>();
    internalTransactions.add(new InternalTransaction());
    when(internalTransactionRepository.findInternalTransactionEntitiesBySenderAccountEntity_Id(anyLong())).thenReturn(internalTransactionEntities);

    // WHEN
    List<InternalTransaction> actualInternalTransactions = internalTransactionDAO.findAllBySenderAccountId(anyLong());

    // THEN
    assertEquals(internalTransactions, actualInternalTransactions);
    verify(internalTransactionRepository).findInternalTransactionEntitiesBySenderAccountEntity_Id(anyLong());
  }

  @Test
  public void addInternalTransaction_shouldReturnOK() {
    // GIVEN
    InternalTransaction internalTransaction = new InternalTransaction();
    internalTransaction.setTransferredAmount(5.00);
    InternalAccount senderInternalAccount = new InternalAccount();
    senderInternalAccount.setId(1L);
    internalTransaction.setSenderInternalAccount(senderInternalAccount);
    InternalAccount recipientInternalAccount = new InternalAccount();
    recipientInternalAccount.setId(2L);
    internalTransaction.setRecipientInternalAccount(recipientInternalAccount);
    InternalTransactionEntity internalTransactionEntity = new InternalTransactionEntity();
    InternalAccountEntity senderInternalAccountEntity = new InternalAccountEntity();
    when(internalAccountRepository.findById(internalTransaction.getSenderInternalAccount().getId())).thenReturn(java.util.Optional.of(senderInternalAccountEntity));
    internalTransactionEntity.setSenderAccountEntity(senderInternalAccountEntity);
    InternalAccountEntity recipientInternalAccountEntity = new InternalAccountEntity();
    when(internalAccountRepository.findById(internalTransaction.getRecipientInternalAccount().getId())).thenReturn(java.util.Optional.of(recipientInternalAccountEntity));
    internalTransactionEntity.setRecipientAccountEntity(recipientInternalAccountEntity);

    // WHEN
    InternalTransaction addedInternalTransaction = internalTransactionDAO.addInternalTransaction(internalTransaction);

    // THEN
    assertEquals(5.00, addedInternalTransaction.getTransferredAmount());
    verify(internalAccountRepository, times(2)).findById(anyLong());
  }

}

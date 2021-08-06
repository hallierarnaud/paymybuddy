package com.openclassrooms.paymybuddy.DAOTest;

import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.domain.object.ExternalTransaction;
import com.openclassrooms.paymybuddy.domain.object.InternalAccount;
import com.openclassrooms.paymybuddy.model.DAO.ExternalTransactionDAO;
import com.openclassrooms.paymybuddy.model.DAO.MapDAO;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.entity.ExternalTransactionEntity;
import com.openclassrooms.paymybuddy.model.entity.InternalAccountEntity;
import com.openclassrooms.paymybuddy.model.repository.ExternalAccountRepository;
import com.openclassrooms.paymybuddy.model.repository.ExternalTransactionRepository;
import com.openclassrooms.paymybuddy.model.repository.InternalAccountRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalTransactionDAOTest {

  @Mock
  private ExternalTransactionRepository externalTransactionRepository;

  @Mock
  private InternalAccountRepository internalAccountRepository;

  @Mock
  private ExternalAccountRepository externalAccountRepository;

  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private ExternalTransactionDAO externalTransactionDAO;

  @Test
  public void findAllById_shouldReturnOk() {
    // GIVEN
    ExternalTransactionEntity externalTransactionEntity = new ExternalTransactionEntity();
    ExternalTransaction externalTransaction = new ExternalTransaction();
    when(externalTransactionRepository.findById(anyLong())).thenReturn(java.util.Optional.of(externalTransactionEntity));

    // WHEN
    ExternalTransaction actualExternalTransaction = externalTransactionDAO.findById(anyLong());

    // THEN
    assertEquals(externalTransaction, actualExternalTransaction);
    verify(externalTransactionRepository).findById(anyLong());
  }

  @Test
  public void findAllById_shouldNotFound() {
    // GIVEN
    ExternalTransactionEntity externalTransactionEntity = new ExternalTransactionEntity();
    externalTransactionEntity.setId(1L);

    // THEN
    assertThrows(NoSuchElementException.class, () -> externalTransactionDAO.findById(anyLong()));
  }

  @Test
  public void addExternalTransaction_shouldReturnOK() {
    // GIVEN
    ExternalTransaction externalTransaction = new ExternalTransaction();
    externalTransaction.setTransferredAmount(5.00);
    InternalAccount internalAccount = new InternalAccount();
    internalAccount.setId(1L);
    externalTransaction.setInternalAccount(internalAccount);
    ExternalAccount externalAccount = new ExternalAccount();
    externalAccount.setId(2L);
    externalTransaction.setExternalAccount(externalAccount);
    ExternalTransactionEntity externalTransactionEntity = new ExternalTransactionEntity();
    InternalAccountEntity internalAccountEntity = new InternalAccountEntity();
    when(internalAccountRepository.findById(externalTransaction.getInternalAccount().getId())).thenReturn(java.util.Optional.of(internalAccountEntity));
    externalTransactionEntity.setInternalAccountEntity(internalAccountEntity);
    ExternalAccountEntity externalAccountEntity = new ExternalAccountEntity();
    when(externalAccountRepository.findById(externalTransaction.getExternalAccount().getId())).thenReturn(java.util.Optional.of(externalAccountEntity));
    externalTransactionEntity.setExternalAccountEntity(externalAccountEntity);

    // WHEN
    ExternalTransaction addedExternalTransaction = externalTransactionDAO.addExternalTransaction(externalTransaction);

    // THEN
    assertEquals(5.00, addedExternalTransaction.getTransferredAmount());
    verify(internalAccountRepository).findById(anyLong());
    verify(externalAccountRepository).findById(anyLong());
  }

}

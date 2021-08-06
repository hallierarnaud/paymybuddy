package com.openclassrooms.paymybuddy.DAOTest;

import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;
import com.openclassrooms.paymybuddy.model.DAO.MapDAO;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ContactRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

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
public class ContactDAOTest {

  @Mock
  private ContactRepository contactRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private MapDAO mapDAO;

  @InjectMocks
  private ContactDAO contactDAO;

  @Test
  public void findAllByUserId_shouldReturnOk() {
    // GIVEN
    List<ContactEntity> contactEntities = new ArrayList<>();
    ContactEntity contactEntity = new ContactEntity();
    UserEntity userEntity = new UserEntity();
    UserEntity userEntityAsContact = new UserEntity();
    contactEntity.setUserEntity(userEntity);
    contactEntity.setUserEntityAsContact(userEntityAsContact);
    contactEntities.add(contactEntity);
    List<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    contacts.add(contact);
    when(contactRepository.findByUserEntity_Id(anyLong())).thenReturn(contactEntities);

    // WHEN
    List<Contact> actualContacts = contactDAO.findAllByUserId(anyLong());

    // THEN
    assertEquals(contacts, actualContacts);
    verify(contactRepository).findByUserEntity_Id(anyLong());
  }

  @Test
  public void addContact_shouldReturnOK() {
    // GIVEN
    Contact contact = new Contact();
    contact.setRelationId(1L);
    contact.setUserId(1L);
    contact.setContactId(2L);
    ContactEntity contactEntity = new ContactEntity();
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);
    when(userRepository.findById(contact.getUserId())).thenReturn(java.util.Optional.of(userEntity));
    contactEntity.setUserEntity(userEntity);
    UserEntity userEntityAsContact = new UserEntity();
    when(userRepository.findById(contact.getContactId())).thenReturn(java.util.Optional.of(userEntityAsContact));
    contactEntity.setUserEntityAsContact(userEntityAsContact);

    // WHEN
    ContactResponse addedContactResponse = contactDAO.addContact(contact);

    // THEN
    assertEquals(1L, addedContactResponse.getUserId());
    verify(userRepository, times(2)).findById(anyLong());
  }

}

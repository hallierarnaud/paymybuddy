package com.openclassrooms.paymybuddy.serviceTest;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.Login;
import com.openclassrooms.paymybuddy.domain.service.ContactService;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

  @Mock
  private ContactDAO contactDAO;

  @Mock
  private LoginDAO loginDAO;

  @InjectMocks
  private ContactService contactService;

  @Test
  public void getContactsByUserId_shouldReturnOk () {
    // GIVEN
    List<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    contact.setUserId(1L);
    contact.setContactId(1L);
    contacts.add(contact);
    when(contactDAO.findAllByUserId(anyLong())).thenReturn(contacts);
    Login login = new Login();
    login.setEmail("test@email.fr");
    when(loginDAO.findByUserId(anyLong())).thenReturn(login);

    // WHEN
    List<Contact> actualContacts = contactService.getContactsByUserId(contact.getUserId());

    // THEN
    assertEquals("test@email.fr", actualContacts.get(0).getContactEmail());
    verify(contactDAO).findAllByUserId(anyLong());
  }

  @Test
  public void addContact_shouldReturnOk () {
    // GIVEN
    ContactRequest contactRequest = new ContactRequest();
    contactRequest.setUserId(1L);
    contactRequest.setContactId(2L);
    List<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    contact.setUserId(1L);
    contact.setContactId(2L);
    contacts.add(contact);
    ContactResponse contactResponse = new ContactResponse();
    contactResponse.setUserId(1L);
    when(contactDAO.addContact(contact)).thenReturn(contactResponse);

    // WHEN
    ContactResponse addedContactResponse = contactService.addContact(contactRequest);

    // THEN
    assertEquals(1L, addedContactResponse.getUserId());
    verify(contactDAO).addContact(any(Contact.class));
  }

  @Test
  public void addContact_shouldReturnAlreadyExist () {
   // GIVEN
    ContactRequest contactRequest = new ContactRequest();
    contactRequest.setUserId(1L);
    contactRequest.setContactId(2L);
    List<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    contact.setContactId(2L);
    contacts.add(contact);

    // WHEN
    when(contactDAO.findAllByUserId(anyLong())).thenReturn(contacts);

    // THEN
    assertThrows(EntityExistsException.class, () -> contactService.addContact(contactRequest));
    verify(contactDAO).findAllByUserId(anyLong());
  }

}

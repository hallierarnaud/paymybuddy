package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityExistsException;

import lombok.Data;

@Data
@Service
public class ContactService {

  @Autowired
  private ContactDAO contactDAO;

  @Autowired
  private LoginDAO loginDAO;

  public List<Contact> getContactsByUserId(final Long userId) {
    List<Contact> contacts = contactDAO.findAllByUserId(userId);
    for (Contact contact : contacts) {
      contact.setContactEmail(loginDAO.findByUserId(contact.getContactId()).getEmail());
    }
    return contacts;
  }

  public ContactResponse addContact(ContactRequest contactRequest) {
    List<Contact> contacts = contactDAO.findAllByUserId(contactRequest.getUserId());
    for (Contact contact : contacts) {
      if (contactRequest.getContactId() == contact.getContactId()) {
        throw new EntityExistsException("contact " + contactRequest.getContactId() + " already exists");
      }
    }
    Contact contact = new Contact();
    contact.setUserId(contactRequest.getUserId());
    contact.setContactId(contactRequest.getContactId());
    return contactDAO.addContact(contact);
  }

}

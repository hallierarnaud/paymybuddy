package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;
import com.openclassrooms.paymybuddy.model.DAO.LoginDAO;
import com.openclassrooms.paymybuddy.model.DAO.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@Service
public class ContactService {

  @Autowired
  private ContactDAO contactDAO;

  @Autowired
  private LoginDAO loginDAO;

  public List<Contact> getContactsByUserId(final Long userId) {
    /*if (contactDAO.findAllByUserId(id) == null) {
      throw new NoSuchElementException("id " + id + " doesn't exist or password is false");
    }*/
    List<Contact> contacts = contactDAO.findAllByUserId(userId);
    for (Contact contact : contacts) {
      contact.setContactEmail(loginDAO.findByUserId(contact.getContactId()).getEmail());
    }
    return contacts;
  }

  public ContactResponse addContact(ContactRequest contactRequest) {
    // TODO : ajouter le renvoi d'un contact déjà existant et corriger la méthode findById
    /*for (Long contactId : contactRequest.getContactIdList()) {
      if (contactDAO.findById(contactId)) {
        throw new EntityExistsException("contact " + contactId + " already exists");
      }
    }*/
    Contact contact = new Contact();
    contact.setUserId(contactRequest.getUserId());
    contact.setContactId(contactRequest.getContactId());
    return contactDAO.addContact(contact);
  }

}

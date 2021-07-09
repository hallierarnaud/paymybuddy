package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;

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

  public List<Contact> getContactsByUserId(final Long id) {
    /*if (contactDAO.findAllByUserId(id) == null) {
      throw new NoSuchElementException("id " + id + " doesn't exist or password is false");
    }*/
    List<Contact> contacts = contactDAO.findAll();
    List<Contact> contactsByUserId = new ArrayList<>();
    for (Contact contact : contacts) {
      if (contact.getUserId() == id) {
        contactsByUserId.add(contact);
      }
    }
    return contactsByUserId;
  }

  public List<ContactResponse> addContact(ContactRequest contactRequest) {
    // TODO : ajouter le renvoi d'un contact déjà existant et corriger la méthode findById
    /*for (Long contactId : contactRequest.getContactIdList()) {
      if (contactDAO.findById(contactId)) {
        throw new EntityExistsException("contact " + contactId + " already exists");
      }
    }*/
    Contact contact = new Contact();
    contact.setUserId(contactRequest.getUserId());
    List<ContactResponse> contacts = new ArrayList<>();
    for (Long contactId : contactRequest.getContactIdList()) {
      contact.setContactId(contactId);
      contacts.add(contactDAO.addContact(contact));
    }
    return contacts;
  }

}

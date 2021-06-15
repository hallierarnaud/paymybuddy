package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import lombok.Data;

@Data
@Service
public class ContactService {

  @Autowired
  private ContactDAO contactDAO;

  public Contact getContact(final Long id) {
    if (contactDAO.findById(id) == null) {
      throw new NoSuchElementException("contact " + id + " doesn't exist");
    }
    return contactDAO.findById(id);
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
    contact.setContactIdList(contactRequest.getContactIdList());
    contactDAO.addContact(contact);
    ContactResponse contactResponse = new ContactResponse();
    contactResponse.setUserId(contactRequest.getUserId());
    contactResponse.setContactIdList(contactRequest.getContactIdList());
    return contactResponse;
  }

}

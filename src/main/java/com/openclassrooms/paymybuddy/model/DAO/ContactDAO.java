package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.ExternalAccount;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.ExternalAccountEntity;
import com.openclassrooms.paymybuddy.model.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class ContactDAO {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  MapDAO mapDAO;

  public Contact findById(Long id) {
    ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("contact " + id + " doesn't exist"));
    Contact contact = new Contact();
    mapDAO.updateContactWithContactEntity(contact, contactEntity);
    return contact;
  }

  public Contact addContact(Contact contact) {
    ContactEntity contactEntity = new ContactEntity();
    mapDAO.updateContactEntityWithContact(contactEntity, contact);
    contactRepository.save(contactEntity);
    return contact;
  }

}

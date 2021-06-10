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

  public List<ContactResponse> addContact(ContactRequest contactRequest) {
    /*if (contactDAO.existsById(contactRequest.getId())) {
      throw new EntityExistsException("contact " + contactRequest.getId() + " already exists");
    }*/
    List<ContactResponse> contactResponseList = new ArrayList<>();
    Contact contact = new Contact();
    contact.setUserId(contactRequest.getUserId());
    contact.setContactIdList(contactRequest.getContactIdList());
    contactDAO.addContact(contact);
    List<Long> relationIdList = contact.getRelationId();
    for (Long relationId : relationIdList) {
      Contact saveContact = contactDAO.findById(relationId);
      ContactResponse contactResponse = new ContactResponse();
      contactResponse.setUserId(saveContact.getUserId());
      contactResponse.setContactIdList(saveContact.getContactIdList());
      contactResponseList.add(contactResponse);
    }
    return contactResponseList;
  }

}

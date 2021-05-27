package com.openclassrooms.paymybuddy.domain.service;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.DAO.ContactDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

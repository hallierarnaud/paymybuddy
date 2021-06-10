package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ContactRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ContactDAO {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  MapDAO mapDAO;

  public Contact findById(Long id) {
    ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("contact " + id + " doesn't exist"));
    Contact contact = new Contact();
    contact.setRelationId(Collections.singletonList(contactEntity.getId()));
    return contact;
  }

  public Contact addContact(Contact contact) {
    List<Long> relationIdList = new ArrayList<>();
    for (Long contactId : contact.getContactIdList()) {
      ContactEntity contactEntity = new ContactEntity();
      UserEntity userEntity = userRepository.findById(contact.getUserId()).orElseThrow(() -> new NoSuchElementException("user " + contact.getUserId() + " doesn't exist"));
      contactEntity.setUserEntity(userEntity);
      UserEntity userEntityAsContact = userRepository.findById(contactId).orElseThrow(() -> new NoSuchElementException("user " + contactId + " doesn't exist"));
      contactEntity.setUserEntityAsContact(userEntityAsContact);
      contactRepository.save(contactEntity);
      relationIdList.add(contactEntity.getId());
    }
    contact.setRelationId(relationIdList);
    return contact;
  }

}

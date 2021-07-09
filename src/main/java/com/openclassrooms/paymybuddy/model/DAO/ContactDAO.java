package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.domain.object.UserAccount;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.LoginEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ContactRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ContactDAO {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  MapDAO mapDAO;

  public List<Contact> findAll() {
    List<ContactEntity> contactEntities =  StreamSupport.stream(contactRepository.findAll().spliterator(),false)
            .collect(Collectors.toList());
    return contactEntities.stream().map((contactEntity) -> {
      Contact contact = new Contact();
      contact.setRelationId(contactEntity.getId());
      contact.setUserId(contactEntity.getUserEntity().getId());
      contact.setContactId(contactEntity.getUserEntityAsContact().getId());
      return contact;
    }).collect(Collectors.toList());
  }

  /*public Contact addContact(Contact contact) {
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
  }*/

}

package com.openclassrooms.paymybuddy.model.DAO;

import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.object.Contact;
import com.openclassrooms.paymybuddy.model.entity.ContactEntity;
import com.openclassrooms.paymybuddy.model.entity.UserEntity;
import com.openclassrooms.paymybuddy.model.repository.ContactRepository;
import com.openclassrooms.paymybuddy.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

  public List<Contact> findAllByUserId(Long userId) {
    List<ContactEntity> contactEntities =  StreamSupport.stream(contactRepository.findByUserEntity_Id(userId).spliterator(),false)
            .collect(Collectors.toList());
    return contactEntities.stream().map((contactEntity) -> {
      Contact contact = new Contact();
      contact.setRelationId(contactEntity.getId());
      contact.setUserId(contactEntity.getUserEntity().getId());
      contact.setContactId(contactEntity.getUserEntityAsContact().getId());
      return contact;
    }).collect(Collectors.toList());
  }

  public ContactResponse addContact(Contact contact) {
    ContactEntity contactEntity = new ContactEntity();
    UserEntity userEntity = userRepository.findById(contact.getUserId()).orElseThrow(() -> new NoSuchElementException("user " + contact.getUserId() + " doesn't exist"));
    contactEntity.setUserEntity(userEntity);
    UserEntity userEntityAsContact = userRepository.findById(contact.getContactId()).orElseThrow(() -> new NoSuchElementException("user " + contact.getContactId() + " doesn't exist"));
    contactEntity.setUserEntityAsContact(userEntityAsContact);
    contactRepository.save(contactEntity);

    ContactResponse contactResponse = new ContactResponse();
    contactResponse.setRelationId(contactEntity.getId());
    contactResponse.setUserId(contactEntity.getUserEntity().getId());
    contactResponse.setContactId(contactEntity.getUserEntityAsContact().getId());
    contactResponse.setContactEmail("TODO");
    return contactResponse;
  }

}

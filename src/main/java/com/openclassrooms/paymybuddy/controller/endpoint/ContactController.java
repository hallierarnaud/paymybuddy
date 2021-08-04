package com.openclassrooms.paymybuddy.controller.endpoint;

import com.openclassrooms.paymybuddy.controller.DTO.ContactRequest;
import com.openclassrooms.paymybuddy.controller.DTO.ContactResponse;
import com.openclassrooms.paymybuddy.domain.service.ContactService;
import com.openclassrooms.paymybuddy.domain.service.MapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

@CrossOrigin(origins="*")
@RestController
public class ContactController {

  @Autowired
  private ContactService contactService;

  @Autowired
  private MapService mapService;

  @GetMapping("/contacts")
  public List<ContactResponse> getContactsByUserId(@RequestParam(name = "userId") long id) {
    return contactService.getContactsByUserId(id).stream().map(p -> mapService.convertContactToContactResponse(p)).collect(Collectors.toList());
  }

  @PostMapping("/contacts")
  public ContactResponse addContact(@RequestBody ContactRequest contactRequest) {
    try {
      return contactService.addContact(contactRequest);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "contact " + contactRequest.getContactId() + " already exists");
    }
  }

}

package com.jobsity.api.contacts.controller;

import com.jobsity.api.contacts.model.ContactDTO;
import com.jobsity.api.contacts.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "Contacts Rest Controller")
@RequestMapping(path = "v1/contacts")
@RequiredArgsConstructor
public class ContactsRestController {

    private final ContactsService contactsService;


    @GetMapping
    public List<ContactDTO> contacts(){
        return contactsService.getContacts();
    }
}

package com.jobsity.api.contacts.service;

import com.jobsity.api.contacts.model.ContactDTO;

import java.util.List;


public interface ContactsService {

    List<ContactDTO> getContacts();
}

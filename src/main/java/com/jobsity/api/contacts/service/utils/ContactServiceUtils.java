package com.jobsity.api.contacts.service.utils;

import com.jobsity.api.contacts.kenectlabs.api.contacts.model.Contact;
import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponseWrapper;
import com.jobsity.api.contacts.model.ContactDTO;

import java.util.List;

public interface ContactServiceUtils {

    List<ContactDTO> getFromGetContactsResponseWrapper(GetContactsResponseWrapper wrapper);

    ContactDTO fromContact(Contact contact);
}

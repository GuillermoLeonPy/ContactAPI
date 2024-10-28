package com.jobsity.api.contacts.service.utils.impl;

import com.jobsity.api.contacts.kenectlabs.api.contacts.model.Contact;
import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponseWrapper;
import com.jobsity.api.contacts.model.ContactDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactServiceUtilsImpl implements com.jobsity.api.contacts.service.utils.ContactServiceUtils {

    protected final String SOURCE = "KENECT_LABS";

    public List<ContactDTO> getFromGetContactsResponseWrapper(GetContactsResponseWrapper wrapper){
        return wrapper.getResponse()
                .getContacts()
                .stream()
                .map(this::fromContact)
                .collect(Collectors.toList());
    }

    public ContactDTO fromContact(Contact contact){
        return  ContactDTO.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .source(SOURCE)
                .name(contact.getName())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}

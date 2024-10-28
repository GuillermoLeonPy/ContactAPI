package com.jobsity.api.contacts.service.impl;

import com.jobsity.api.contacts.kenectlabs.api.contacts.client.RestClient;
import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponseWrapper;
import com.jobsity.api.contacts.model.ContactDTO;
import com.jobsity.api.contacts.service.ContactsService;
import com.jobsity.api.contacts.service.utils.ContactServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactsServiceImpl implements ContactsService {

    protected final RestClient restClient;
    protected final ContactServiceUtils contactServiceUtils;

    public List<ContactDTO> getContacts(){
        GetContactsResponseWrapper response = restClient.getContacts();
        List<ContactDTO> contactList = contactServiceUtils.getFromGetContactsResponseWrapper(response);
        int currentPage = response.getCurrentPage();
        int totalPages = response.getTotalPages();
        for(int page = currentPage + 1; page <= totalPages; page++){
            response = restClient.getContacts(page);
            List<ContactDTO> tmpList = contactServiceUtils.getFromGetContactsResponseWrapper(response);
            contactList.addAll(tmpList);
        }
        return contactList;
    }
}

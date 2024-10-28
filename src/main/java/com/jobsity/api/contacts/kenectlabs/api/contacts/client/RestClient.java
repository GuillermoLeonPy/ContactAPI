package com.jobsity.api.contacts.kenectlabs.api.contacts.client;

import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponseWrapper;


public interface RestClient {

    GetContactsResponseWrapper getContacts(int page);

    GetContactsResponseWrapper getContacts();

}

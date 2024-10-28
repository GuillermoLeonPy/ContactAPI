package com.jobsity.api.contacts.kenectlabs.api.contacts.client.impl;

import com.jobsity.api.contacts.kenectlabs.api.contacts.client.RestClient;
import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponse;
import com.jobsity.api.contacts.kenectlabs.api.contacts.model.GetContactsResponseWrapper;
import com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils.RestClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestClientImpl implements RestClient {
    @Value("${kenectlabs.api.contacts.url}")
    protected String url;

    protected final RestTemplate restTemplate;
    protected final RestClientUtils restClientUtils;


    public GetContactsResponseWrapper getContacts(int page){
        ResponseEntity<GetContactsResponse> entity = restTemplate.getForEntity(url + "?page=" + page,GetContactsResponse.class);
        return getGetContactsResponseWrapper(entity);
    }

    public GetContactsResponseWrapper getContacts(){
        ResponseEntity<GetContactsResponse> entity = restTemplate.getForEntity(url,GetContactsResponse.class);
        return getGetContactsResponseWrapper(entity);
    }

    protected GetContactsResponseWrapper getGetContactsResponseWrapper(ResponseEntity<GetContactsResponse> entity){
        return GetContactsResponseWrapper.builder()
                .response(entity.getBody())
                .currentPage(restClientUtils.getNumberValueHeader(entity.getHeaders(),RestClientUtils.CURRENT_PAGE))
                .pageItems(restClientUtils.getNumberValueHeader(entity.getHeaders(),RestClientUtils.PAGE_ITEMS))
                .totalPages(restClientUtils.getNumberValueHeader(entity.getHeaders(),RestClientUtils.TOTAL_PAGES))
                .totalElements(restClientUtils.getNumberValueHeader(entity.getHeaders(),RestClientUtils.TOTAL_COUNT))
                .build();
    }

}

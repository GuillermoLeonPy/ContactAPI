package com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils;

import org.springframework.http.HttpHeaders;

public interface RestClientUtils {

    static final String CURRENT_PAGE = "Current-Page";
    static final String PAGE_ITEMS = "Page-Items";
    static final String TOTAL_PAGES = "Total-Pages";
    static final String TOTAL_COUNT = "Total-Count";

    String extractHeader(HttpHeaders headers, String key);

    int getNumberValueHeader(HttpHeaders headers, String key);
}

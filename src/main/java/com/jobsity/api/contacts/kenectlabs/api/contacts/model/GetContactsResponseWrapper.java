package com.jobsity.api.contacts.kenectlabs.api.contacts.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class GetContactsResponseWrapper {
    private GetContactsResponse response;
    private int currentPage;
    private int totalPages;
    private int pageItems;
    private int totalElements;
    private String link;
}

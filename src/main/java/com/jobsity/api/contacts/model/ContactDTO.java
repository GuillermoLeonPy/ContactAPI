package com.jobsity.api.contacts.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ContactDTO {
    private Integer id;
    private String name;
    private String email;
    private String source;
    private String createdAt;
    private String updatedAt;
}

package com.jobsity.api.contacts.kenectlabs.api.contacts.model.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericExceptionMessage {
    private String message;
    private String messageCode;

}

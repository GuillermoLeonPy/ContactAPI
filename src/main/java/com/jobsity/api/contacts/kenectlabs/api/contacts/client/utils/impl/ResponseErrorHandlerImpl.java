package com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ResponseErrorHandlerImpl implements ResponseErrorHandler {
    protected final String KENECT_LABS_API = "Kenect Labs API";
    protected final String REST_CLIENT_GENERIC_CLIENT_ERROR = "rest.client.generic.client.side.error";
    protected final String REST_CLIENT_GENERIC_SERVER_ERROR = "rest.client.generic.server.side.error";
    private final MessageSource messageSource;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String messageCode = response.getStatusCode().is4xxClientError() ? REST_CLIENT_GENERIC_CLIENT_ERROR :REST_CLIENT_GENERIC_SERVER_ERROR;
        throw new RuntimeException(messageSource.getMessage(messageCode,new Object[]{KENECT_LABS_API}, Locale.ENGLISH));

    }
}

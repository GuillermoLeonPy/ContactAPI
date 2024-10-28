package com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientRequestInterceptorImpl implements ClientHttpRequestInterceptor {

    @Value("${kenectlabs.api.contacts.bearer-token}")
    protected String bearerToken;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", "Bearer " + bearerToken);
        return execution.execute(request, body);
    }
}

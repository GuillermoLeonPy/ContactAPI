package com.jobsity.api.contacts.kenectlabs.api.contacts.client.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestClientConfiguration {

    protected final int connectionTimeout;
    protected final int readTimeOut;
    protected final String bearerToken;

    protected final ResponseErrorHandler responseErrorHandler;

    public RestClientConfiguration(
            @Value("${kenectlabs.api.contacs.connectionTimeout:3000}") int connectionTimeout,
            @Value("${kenectlabs.api.contacs.readTimeOut:3000}") int readTimeOut,
            @Value("${kenectlabs.api.contacts.bearer-token}") String bearerToken,
            ResponseErrorHandler responseErrorHandler) {
        this.connectionTimeout = connectionTimeout;
        this.readTimeOut = readTimeOut;
        this.bearerToken = bearerToken;
        this.responseErrorHandler = responseErrorHandler;
    }

    @Bean
    protected RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){
        RestTemplate restTemplate = new RestTemplate(factory());
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

    protected SimpleClientHttpRequestFactory factory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeOut);;

        return factory;
    }
}

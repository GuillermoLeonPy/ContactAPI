package com.jobsity.api.contacts.controller.exception.handler;

import com.jobsity.api.contacts.kenectlabs.api.contacts.model.exception.GenericExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    protected final MessageSource messageSource;
    protected final String GENERIC_EXCEPTION_MESSAGE = "exception.generic.message";
    protected final String MESSAGE_CODE = ".code";


    protected GenericExceptionMessage loadMessage(String messageKey, String codeKey, Object[] params, Locale locale){
        return GenericExceptionMessage.builder()
                        .message(messageSource.getMessage(messageKey,params, locale))
                        .messageCode(messageSource.getMessage(codeKey,null,locale))
                        .build();
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<?> globalExceptionHandler(Throwable t){
        GenericExceptionMessage message = loadMessage(GENERIC_EXCEPTION_MESSAGE, GENERIC_EXCEPTION_MESSAGE.concat(MESSAGE_CODE), new Object[]{t.getMessage()},Locale.ENGLISH);
        return new ResponseEntity<GenericExceptionMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}

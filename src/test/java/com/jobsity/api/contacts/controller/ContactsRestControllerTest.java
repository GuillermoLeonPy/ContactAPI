package com.jobsity.api.contacts.controller;

import com.jobsity.api.contacts.application.Application;
import com.jobsity.api.contacts.service.ContactsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
@ContextConfiguration
@ActiveProfiles(value = "dev")
class ContactsRestControllerTest {

    private final String API_CONTROLLER_PATH = "/v1/contacts";

    private final String USER_PASS = "guest";
    private final String USER_ROLE = "GUEST";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ContactsService contactsService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName(value = "Successfully retrieve contacts")
    void contacts() throws Exception {

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(API_CONTROLLER_PATH)
                        .headers(getHeaders())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(USER_PASS,USER_PASS))
                        .with(SecurityMockMvcRequestPostProcessors.user(USER_PASS).password(USER_PASS).roles(USER_ROLE))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(25)))
                .andReturn();

    }

    @Test
    @DisplayName(value = "Unauthorized request")
    void contactsUnauthorized() throws Exception {
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get(API_CONTROLLER_PATH)
                                .headers(getHeaders())
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .with(SecurityMockMvcRequestPostProcessors.httpBasic("user","123"))
                                .with(SecurityMockMvcRequestPostProcessors.user(USER_PASS).password(USER_PASS).roles(USER_ROLE))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();

    }


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host","localhost:8080");
        headers.add("Accept","*/*");
        headers.add("Cache-Control","no-cache");
        headers.add("Connection","keep-alive");
        headers.add("Accept-Encoding","gzip, deflate, br");
        return headers;
    }


}
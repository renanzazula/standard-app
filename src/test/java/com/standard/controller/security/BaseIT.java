package com.standard.controller.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class BaseIT {

    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    public static Stream<Arguments> getStreamAllUsers() {
        return Stream.of(
                Arguments.of("admin", "spring"),
                Arguments.of("user", "spring"),
                Arguments.of("customer", "spring")
        );
    }

    public static Stream<Arguments> getStreamUser() {
        return Stream.of(Arguments.of("user", "spring"));
    }

    public static Stream<Arguments> getStreamCustomer() {
        return Stream.of(Arguments.of("customer", "spring"));
    }

    public static Stream<Arguments> getStreamAdmin() {
        return Stream.of(Arguments.of("admin", "spring"));
    }

}

package com.standard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

abstract class AbstractRestControllerTest extends BaseTest {

    @Autowired
    protected WebApplicationContext wac;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

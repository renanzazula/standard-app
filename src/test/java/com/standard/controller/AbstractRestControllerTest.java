package com.standard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.BaseTest;

public abstract class AbstractRestControllerTest extends BaseTest {


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

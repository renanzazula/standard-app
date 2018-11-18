package com.standard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.BaseTest;

abstract class AbstractRestControllerTest extends BaseTest {


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

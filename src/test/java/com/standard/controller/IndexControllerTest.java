package com.standard.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {IndexControllerTest.class})
public class IndexControllerTest extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    
//    @Test
//    void testGetIndexSlashPrivate() throws Exception{
//        mockMvc.perform(get("/private" ))
//                .andExpect(status().isUnauthorized());
//    }
}

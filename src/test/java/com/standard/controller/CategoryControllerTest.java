package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTest extends AbstractRestControllerTest {

    @Autowired
    WebApplicationContext wac;

    @MockBean
    CategoryService service;

    MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
        
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        setUpCategory();
    }
    
    @Test
    void testTryToAccessPrivateUnauthorizedGet() throws Exception{
        mockMvc.perform(get(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkGet() throws Exception{
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated()); 
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkPost() throws Exception{
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
    public void testFindAll() throws Exception {
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("bob");

        List<Category> categories = Arrays.asList(category, category2);
        
        when(service.findAll()).thenReturn(categories);
        
        mockMvc.perform(get(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    

    @Test
    public void testCreate() throws Exception {
        when(service.create(category)).thenReturn(category);
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(CategoryController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {
        when(service.update(1L, category)).thenReturn(category);
        mockMvc.perform(put(CategoryController.BASE_URL+"/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }
}

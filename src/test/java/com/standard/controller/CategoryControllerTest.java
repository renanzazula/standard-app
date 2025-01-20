package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CategoryController.class})
class CategoryControllerTest extends AbstractRestControllerTest {

    @Autowired
    WebApplicationContext wac;

    @MockitoBean
    CategoryService service;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        setUpCategory();
    }
    
    @Test
    void testTryToAccessPrivateUnauthorizedGet() throws Exception{
        mockMvc.perform(get(CategoryController.BASE_URL)).andExpect(status().isUnauthorized());
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkGet() throws Exception{
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated()); 
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkPost() throws Exception{
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
     void testFindAll() throws Exception {
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("bob");

        List<Category> categories = Arrays.asList(category, category2);
        
        when(service.findAll()).thenReturn(categories);
        
        mockMvc.perform(get(CategoryController.BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    

    @Test
     void testCreate() throws Exception {
        when(service.create(category)).thenReturn(category);
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
     void testDelete() throws Exception {
        mockMvc.perform(delete(CategoryController.BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
     void testUpdate() throws Exception {
        when(service.update(1L, category)).thenReturn(category);
        mockMvc.perform(put(CategoryController.BASE_URL+"/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtCategoryRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }
}

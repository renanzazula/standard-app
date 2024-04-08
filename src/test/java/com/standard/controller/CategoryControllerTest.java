package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.categoria.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CategoryController.class})
public class CategoryControllerTest extends AbstractRestControllerTest {

    @MockBean
    CategoryService service;

    @Autowired
    MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
        
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        setUpCategoria();
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
    public void testConsultar() throws Exception {
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
    public void testIncluir() throws Exception {
        when(service.save(category)).thenReturn(category);
        mockMvc.perform(post(CategoryController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(CategoryController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.update(1L, category)).thenReturn(category);
        mockMvc.perform(put(CategoryController.BASE_URL+"/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}

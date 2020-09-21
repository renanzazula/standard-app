package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CategoriaController.class})
public class CategoriaControllerTest extends AbstractRestControllerTest {

    @MockBean
    CategoriaService service;

    @Autowired
    MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
        
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();

        setUpCategoria();
    }
    
    @Test
    void testTryToAccessPrivateUnauthorizedGet() throws Exception{
        mockMvc.perform(get(CategoriaController.BASE_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkGet() throws Exception{
        mockMvc.perform(post(CategoriaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isCreated()); 
    }

    @Test
    void testTryToAccessPrivateAuthorizedIsOkPost() throws Exception{
        mockMvc.perform(post(CategoriaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
    public void testConsultar() throws Exception {
        Categoria categoria2 = new Categoria();
        categoria2.setCodigo(2L);
        categoria2.setNome("bob");

        List<Categoria> categorias = Arrays.asList(categoria, categoria2);
        
        when(service.consultar()).thenReturn(categorias);
        
        mockMvc.perform(get(CategoriaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(categoria)).thenReturn(categoria);
        mockMvc.perform(post(CategoriaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(CategoriaController.BASE_URL + "/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1L,categoria)).thenReturn(categoria);
        mockMvc.perform(put(CategoriaController.BASE_URL+"/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}

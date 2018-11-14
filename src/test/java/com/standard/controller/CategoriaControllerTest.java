package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CategoriaController.class})
public class CategoriaControllerTest extends AbstractRestControllerTest {

    @MockBean
    CategoriaService service;

    @Autowired
    MockMvc mockMvc;

    

    @Before
    public void setUp() throws Exception {
        setUpCategoria();
    }

    @Test
    public void testConsultar() throws Exception {
        Categoria categoria2 = new Categoria();
        categoria2.setCodigo(2l);
        categoria2.setNome("bob");

        List<Categoria> categorias = Arrays.asList(categoria, categoria2);
        when(service.consultar()).thenReturn(categorias);
        mockMvc.perform(get(CategoriaController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(categoria.getCodigo())).thenReturn(categoria);
        mockMvc.perform(get(CategoriaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(categoria)).thenReturn(categoria);
        mockMvc.perform(post(CategoriaController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(CategoriaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1l,categoria)).thenReturn(categoria);
        mockMvc.perform(put(CategoriaController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
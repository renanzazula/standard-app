package com.standard.controller;

import com.standard.domain.Marca;
import com.standard.service.marca.MarcaService;
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
@WebMvcTest(controllers = {MarcaController.class})
public class MarcaControllerTest extends AbstractRestControllerTest {

    @MockBean
    MarcaService service;

    @Autowired
    MockMvc mockMvc;

    private Marca obj = null;

    @BeforeEach
    public void setUp() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();
        
        obj = new Marca();
        obj.setCodigo(1L);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        Marca marca2 = new Marca();
        marca2.setCodigo(2L);
        marca2.setNome("bob");

        List<Marca> marcas = Arrays.asList(obj, marca2);
        when(service.consultar()).thenReturn(marcas);
        mockMvc.perform(get(MarcaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(MarcaController.BASE_URL + "/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(obj)).thenReturn(obj);
        mockMvc.perform(post(MarcaController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(MarcaController.BASE_URL + "/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1L,obj)).thenReturn(obj);
        mockMvc.perform(put(MarcaController.BASE_URL+"/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}

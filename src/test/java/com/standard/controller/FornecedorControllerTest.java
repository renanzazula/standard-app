package com.standard.controller;

import com.standard.domain.Fornecedor;
import com.standard.service.fornecedor.FornecedorService;
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
@WebMvcTest(controllers = {FornecedorController.class})
public class FornecedorControllerTest extends AbstractRestControllerTest {

    @MockBean
    FornecedorService service;

    @Autowired
    MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
       setUpFornecedor();
    }

    @Test
    public void testConsultar() throws Exception {
        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setCodigo(2L);
        fornecedor2.setNome("bob");

        List<Fornecedor> fornecedors = Arrays.asList(fornecedor, fornecedor2);
        when(service.consultar()).thenReturn(fornecedors);
        mockMvc.perform(get(FornecedorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(fornecedor.getCodigo())).thenReturn(fornecedor);
        mockMvc.perform(get(FornecedorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(fornecedor)).thenReturn(fornecedor);
        mockMvc.perform(post(FornecedorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fornecedor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(FornecedorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1L,fornecedor)).thenReturn(fornecedor);
        mockMvc.perform(put(FornecedorController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fornecedor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
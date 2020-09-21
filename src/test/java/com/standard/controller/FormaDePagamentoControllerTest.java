package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.service.formaDePagamento.FormaDePagamentoService;
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
@WebMvcTest(controllers = {FormasDePagamentoController.class})
public class FormaDePagamentoControllerTest extends AbstractRestControllerTest {

    @MockBean
    FormaDePagamentoService service;

    @Autowired
    MockMvc mockMvc;

    private FormasDePagamento obj = null;

    @BeforeEach
    public void setUp() {

//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();
        
        obj = new FormasDePagamento();
        obj.setCodigo(1L);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        FormasDePagamento formaDePagamento2 = new FormasDePagamento();
        formaDePagamento2.setCodigo(2L);
        formaDePagamento2.setNome("bob");

        List<FormasDePagamento> formaDePagamentos = Arrays.asList(obj, formaDePagamento2);
        when(service.consultar()).thenReturn(formaDePagamentos);
        mockMvc.perform(get(FormasDePagamentoController.BASE_URL)
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(FormasDePagamentoController.BASE_URL + "/1")
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
        mockMvc.perform(post(FormasDePagamentoController.BASE_URL)
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
        mockMvc.perform(delete(FormasDePagamentoController.BASE_URL + "/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1L, obj)).thenReturn(obj);
        mockMvc.perform(put(FormasDePagamentoController.BASE_URL + "/1")
                .header(API_KEY, API_KEY_VALUE)
                .header(API_SECRET, API_SECRET_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}

package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.service.FormaDePagamento.FormaDePagamentoService;
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
@WebMvcTest(controllers = {FormaDePagamentoController.class})
public class FormaDePagamentoControllerTest extends AbstractRestControllerTest {

    @MockBean
    FormaDePagamentoService service;

    @Autowired
    MockMvc mockMvc;

    FormasDePagamento obj = null;

    @Before
    public void setUp() throws Exception {
        obj = new FormasDePagamento();
        obj.setCodigo(1);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        FormasDePagamento formaDePagamento2 = new FormasDePagamento();
        formaDePagamento2.setCodigo(2);
        formaDePagamento2.setNome("bob");

        List<FormasDePagamento> formaDePagamentos = Arrays.asList(obj, formaDePagamento2);
        when(service.consultar()).thenReturn(formaDePagamentos);
        mockMvc.perform(get(FormaDePagamentoController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(FormaDePagamentoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(obj)).thenReturn(obj);
        mockMvc.perform(post(FormaDePagamentoController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(FormaDePagamentoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1, obj)).thenReturn(obj);
        mockMvc.perform(put(FormaDePagamentoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
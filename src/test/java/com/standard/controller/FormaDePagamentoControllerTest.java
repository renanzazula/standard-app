package com.standard.controller;

import com.standard.domain.PaymentMethod;
import com.standard.service.paymentmethod.PaymentMethodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {PaymentMethodController.class})
public class FormaDePagamentoControllerTest extends AbstractRestControllerTest {

    @MockBean
    PaymentMethodService service;

    @Autowired
    MockMvc mockMvc;

    private PaymentMethod obj = null;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        
        obj = new PaymentMethod();
        obj.setId(1L);
        obj.setName(NOME);
        obj.setDescription(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        PaymentMethod formaDePagamento2 = new PaymentMethod();
        formaDePagamento2.setId(2L);
        formaDePagamento2.setName("bob");

        List<PaymentMethod> formaDePagamentos = Arrays.asList(obj, formaDePagamento2);
        when(service.findAll()).thenReturn(formaDePagamentos);
        mockMvc.perform(get(PaymentMethodController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.findById(obj.getId())).thenReturn(obj);
        mockMvc.perform(get(PaymentMethodController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.create(obj)).thenReturn(obj);
        mockMvc.perform(post(PaymentMethodController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(PaymentMethodController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.update(1L, obj)).thenReturn(obj);
        mockMvc.perform(put(PaymentMethodController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}

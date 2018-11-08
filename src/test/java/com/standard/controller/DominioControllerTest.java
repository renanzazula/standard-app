package com.standard.controller;

import com.standard.domain.Dominio;
import com.standard.service.dominio.DominioService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DominioControllerTest {

    public static final String NAME = "Jim";

    @Mock
    DominioService service;

    @InjectMocks
    DominioController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testConsultar() throws Exception {

        Dominio dominio = new Dominio();
        dominio.setCodigo(1);
        dominio.setNome(NAME);

        Dominio dominio2 = new Dominio();
        dominio2.setCodigo(2);
        dominio2.setNome("bob");

        List<Dominio> dominios = Arrays.asList(dominio, dominio2);

        when(service.consultar()).thenReturn(dominios);

        mockMvc.perform(get("/api/v1/dominio/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
        //.andExpect(jsonPath("$.dominios", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() {

    }

    @Test
    public void testIncluir() {

    }

    @Test
    public void testDelete() {

    }

    @Test
    public void testAlterar() {

    }
}
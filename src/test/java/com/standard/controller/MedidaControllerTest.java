package com.standard.controller;

import com.standard.domain.Medida;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
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
@WebMvcTest(controllers = {MedidaController.class})
public class MedidaControllerTest extends AbstractRestControllerTest {

    @MockBean
    MedidaService service;

    @MockBean
    CategoriaService categoriaService;

    @MockBean
    MarcaService marcaService;

    @Autowired
    MockMvc mockMvc;



    @BeforeEach
    public void setUp() {
        setUpMedida();
    }

    @Test
    public void testConsultar() throws Exception {
        Medida medida2 = new Medida();
        medida2.setCodigo(2L);
        medida2.setNome("bob");

        List<Medida> medidas = Arrays.asList(medida, medida2);
        when(service.consultar()).thenReturn(medidas);
        mockMvc.perform(get(MedidaController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        setUpMedida();
        when(service.consultarByCodigo(medida.getCodigo())).thenReturn(medida);
        mockMvc.perform(get(MedidaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.nome", equalTo(NOME)))
                //.andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(medida)).thenReturn(medida);
        mockMvc.perform(post(MedidaController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medida)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(MedidaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1L, medida)).thenReturn(medida);
        mockMvc.perform(put(MedidaController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medida)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
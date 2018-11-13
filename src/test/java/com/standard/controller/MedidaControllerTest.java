package com.standard.controller;

import com.standard.domain.Medida;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
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

    Medida obj = null;

    @Before
    public void setUp() throws Exception {
        obj = new Medida();
        obj.setCodigo(1);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        Medida medida2 = new Medida();
        medida2.setCodigo(2);
        medida2.setNome("bob");

        List<Medida> medidas = Arrays.asList(obj, medida2);
        when(service.consultar()).thenReturn(medidas);
        mockMvc.perform(get(MedidaController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(MedidaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(obj)).thenReturn(obj);
        mockMvc.perform(post(MedidaController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
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
        when(service.alterar(1,obj)).thenReturn(obj);
        mockMvc.perform(put(MedidaController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
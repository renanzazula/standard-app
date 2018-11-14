package com.standard.controller;

import com.standard.domain.SubCategoria;
import com.standard.service.subCategoria.SubCategoriaService;
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
@WebMvcTest(controllers = {SubCategoriaController.class})
public class SubCategoriaControllerTest extends AbstractRestControllerTest {

    @MockBean
    SubCategoriaService service;

    @Autowired
    MockMvc mockMvc;

    SubCategoria obj = null;

    @Before
    public void setUp() throws Exception {
        obj = new SubCategoria();
        obj.setCodigo(1l);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        SubCategoria subCategoria2 = new SubCategoria();
        subCategoria2.setCodigo(2l);
        subCategoria2.setNome("bob");

        List<SubCategoria> subCategorias = Arrays.asList(obj, subCategoria2);
        when(service.consultar()).thenReturn(subCategorias);
        mockMvc.perform(get(SubCategoriaController.BASE_URL + "/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(service.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(SubCategoriaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(service.incluir(obj)).thenReturn(obj);
        mockMvc.perform(post(SubCategoriaController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(SubCategoriaController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
        when(service.alterar(1l,obj)).thenReturn(obj);
        mockMvc.perform(put(SubCategoriaController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(obj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }
}
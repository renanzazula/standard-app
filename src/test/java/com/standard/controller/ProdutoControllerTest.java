package com.standard.controller;

import com.standard.domain.Produto;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.dominio.DominioService;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ProdutoController.class})
public class ProdutoControllerTest extends AbstractRestControllerTest {

    // TODO:

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private CategoriaService categoriaService;

    @MockBean
    private MedidaService medidaService;

    @MockBean
    private DominioService dominioService;

    Produto obj = null;

    @Before
    public void setUp() throws Exception {
        obj = new Produto();
        obj.setCodigo(1);
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
    }

    @Test
    public void testConsultar() throws Exception {
        Produto produto2 = new Produto();
        produto2.setCodigo(2);
        produto2.setNome("bob");

        List<Produto> produtos = Arrays.asList(obj, produto2);

        when(produtoService.consultar()).thenReturn(produtos);

        mockMvc.perform(get(ProdutoController.BASE_URL + "/all")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].nome", is(NOME)));
                // Todo: others fields
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(produtoService.consultarByCodigo(obj.getCodigo())).thenReturn(obj);
        mockMvc.perform(get(ProdutoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() throws Exception {
        when(produtoService.incluir(obj)).thenReturn(obj);

        mockMvc.perform(post(ProdutoController.BASE_URL)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(obj)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.nome", equalTo(NOME)))
               .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ProdutoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() throws Exception {
//        when(produtoService.alterar(1,obj)).thenReturn(obj);
//        mockMvc.perform(put(ProdutoController.BASE_URL + "/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(obj)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nome", equalTo(NOME)))
//                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }    
    //
    @Test
    public void consultaSubCategoriaByCategoria() {
    }

    @Test
    public void addicionarProduto() {
    }

    @Test
    public void ajaxConsultarItensMedidaByCategoria() {
    }

    @Test
    public void ajaxConsultarItensMedidaByProdutoCodigo() {
    }

    @Test
    public void ajaxConsultarItensMedidaByMedidaCodigo() {
    }

    @Test
    public void ajaxObterDominios() {
    }
}
package com.standard.controller;

import com.standard.domain.Produto;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.dominio.DominioService;
import com.standard.service.fornecedor.FornecedorService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
import com.standard.service.subcategoria.SubCategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProdutoController.class})
public class ProdutoControllerTest extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private CategoriaService categoriaService;

    @MockBean
    private MedidaService medidaService;

    @MockBean
    private MarcaService marcaService;

    @MockBean
    private FornecedorService fornecedorService;


    @MockBean
    private SubCategoriaService subCategoriaService;


    @MockBean
    private DominioService dominioService;

    @BeforeEach
    public void setUp() {
        // requeridos
        setUpMarca();
        when(marcaService.incluir(marca)).thenReturn(marca);

        setUpFornecedor();
        when(fornecedorService.incluir(fornecedor)).thenReturn(fornecedor);

        setUpSubCategoria();
        when(subCategoriaService.incluir(subCategoria)).thenReturn(subCategoria);

        setUpCategoria();
        categoria.setSubcategorias(new ArrayList<>());
        categoria.getSubcategorias().add(subCategoria);
        when(categoriaService.incluir(categoria)).thenReturn(categoria);

        setUpDominio();
        when(dominioService.incluir(dominio)).thenReturn(dominio);

        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategoria(subCategoria);
        medida.setCategoria(categoria);
        medida.setMarca(marca);
        medida.setItensTipoMedida(itensTipoMedida);
        when(medidaService.incluir(medida)).thenReturn(medida);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        produto.setMarca(marca);
        produto.setFornecedor(fornecedor);
        produto.setCategoria(categoria);
        produto.setMedida(medida);
        produto.setSubCategoria(subCategoria);
        produto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);
    }

    @Test
    public void testConsultar() throws Exception {
        Produto produto2 = new Produto();
        produto2.setCodigo(2L);
        produto2.setNome("bob");

        List<Produto> produtos = Arrays.asList(produto, produto2);

        when(produtoService.consultar()).thenReturn(produtos);

        mockMvc.perform(get(ProdutoController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is(NOME)));
        // Todo: others fields
    }

    @Test
    public void testConsultarByCodigo() throws Exception {
        when(produtoService.consultarByCodigo(produto.getCodigo())).thenReturn(produto);
        mockMvc.perform(get(ProdutoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NOME)))
                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testIncluir() {
//        when(produtoService.incluir(produto)).thenReturn(produto);
//
//        mockMvc.perform(post(ProdutoController.BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(produto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nome", equalTo(NOME)))
//                .andExpect(jsonPath("$.descricao", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ProdutoController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAlterar() {
//        when(produtoService.alterar(1,medida)).thenReturn(medida);
//        mockMvc.perform(put(ProdutoController.BASE_URL + "/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(medida)))
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
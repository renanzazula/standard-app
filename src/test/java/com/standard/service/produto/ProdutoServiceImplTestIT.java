package com.standard.service.produto;

import com.standard.BaseTest;
import com.standard.domain.Fornecedor;
import com.standard.domain.Marca;
import com.standard.domain.Produto;
import com.standard.enums.StatusEnum;
import com.standard.repository.*;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.categoria.CategoriaServiceImpl;
import com.standard.service.dominio.DominioService;
import com.standard.service.dominio.DominioServiceImpl;
import com.standard.service.fornecedor.FornecedorService;
import com.standard.service.fornecedor.FornecedorServiceImpl;
import com.standard.service.marca.MarcaService;
import com.standard.service.marca.MarcaServiceImpl;
import com.standard.service.medida.MedidaService;
import com.standard.service.medida.MedidaServiceImpl;
import com.standard.service.subcategoria.SubcategoriaService;
import com.standard.service.subcategoria.SubcategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProdutoServiceImplTestIT extends BaseTest {


    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private DominioRepository dominioRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensTipoMedidaRepository itensTipoMedidaRepository;

    private MarcaService marcaService;
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;
    private SubcategoriaService subcategoriaService;
    private CategoriaService categoriaService;
    private DominioService dominioService;
    private MedidaService medidaService;



    @BeforeEach
    public void setUp() {

        marcaService = new MarcaServiceImpl(marcaRepository);
        subcategoriaService = new SubcategoriaServiceImpl(subcategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subcategoriaRepository);
        fornecedorService = new FornecedorServiceImpl(fornecedorRepository);
        dominioService = new DominioServiceImpl(dominioRepository);
        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository, subcategoriaRepository,
                marcaRepository);
        produtoService = new ProdutoServiceImpl(produtoRepository, medidaRepository,
                dominioRepository, fornecedorRepository,
                categoriaRepository, subcategoriaRepository,
                marcaRepository, itensTipoMedidaRepository);

        // requeridos
        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpFornecedor();
        fornecedor = fornecedorService.incluir(fornecedor);

        setUpSubCategoria();
        subcategoria = subcategoriaService.incluir(subcategoria);

        setUpCategoria();
        categoria.setSubcategorias(new ArrayList<>());
        categoria.getSubcategorias().add(subcategoria);
        categoria = categoriaService.incluir(categoria);

        setUpDominio();
        dominio = dominioService.incluir(dominio);

        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategoria(subcategoria);
        medida.setCategoria(categoria);
        medida.setMarca(marca);
        medida.setItensTipoMedida(itensTipoMedida);
        medida = medidaService.incluir(medida);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        produto.setMarca(marca);
        produto.setFornecedor(fornecedor);
        produto.setCategoria(categoria);
        produto.setMedida(medida);
        produto.setSubcategoria(subcategoria);
        produto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);


    }

    @Test
    public void incluir() {
        Produto produtoSave = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produtoSave.getCodigo());
        assertProduto(found, produtoSave);
    }

    @Test
    public void alterar() {
        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setBarCode(BAR_0_CODE + "_update");
        found.setNome(NOME + "_update");
        found.setStatus(StatusEnum.Inativo);
        found.setDescricao(DESCRICAO + "_update");
        found.setPreco(15d);
        found.setPrecoVenda(15d);
        found.setPreco(15d);
        found.setPrecoCusto(15d);
        found.setPrecoOferta(15d);
        found.setDesconto(15d);
        found.setPeso(15d);
        found.setPorcentagem(2);
        found.setPorcentagemDesconto(2);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);
        assertEquals(found.getCodigo(), updated.getCodigo());
        assertEquals(found.getBarCode(), updated.getBarCode());
        assertEquals(found.getNome(), updated.getNome());
        assertEquals(found.getStatus(), updated.getStatus());
        assertEquals(found.getDescricao(), updated.getDescricao());
        assertEquals(found.getPreco(), updated.getPreco());
        assertEquals(found.getPrecoVenda(), updated.getPrecoVenda());
        assertEquals(found.getPreco(), updated.getPreco());
        assertEquals(found.getPrecoCusto(), updated.getPrecoCusto());
        assertEquals(found.getPrecoOferta(), updated.getPrecoOferta());
        assertEquals(found.getDesconto(), updated.getDesconto());
        assertEquals(found.getPeso(), updated.getPeso());
        assertEquals(found.getPorcentagem(), updated.getPorcentagem());
        assertEquals(found.getPorcentagemDesconto(), updated.getPorcentagemDesconto());

        assertMarca(found.getMarca(), updated.getMarca());
        assertCategoria(found.getCategoria(), updated.getCategoria());
        assertSubCategoria(found.getSubcategoria(), updated.getSubcategoria());
        assertFornecedor(found.getFornecedor(), updated.getFornecedor());
        assertMarcaSubCategoriaCategoriaValor(found.getMedida());
        assertEquals(found.getProdutoHasItensTipoMedida().size(), updated.getProdutoHasItensTipoMedida().size());

        for (int i = 0; i < found.getProdutoHasItensTipoMedida().size(); i++) {

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getDominios().size(),
                    updated.getProdutoHasItensTipoMedida().get(i).getDominios().size());

            for (int j = 0; j < found.getProdutoHasItensTipoMedida().get(i).getDominios().size(); j++) {
                assertDominios(found.getProdutoHasItensTipoMedida().get(i).getDominios().get(j),
                        updated.getProdutoHasItensTipoMedida().get(i).getDominios().get(j));
            }

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getQuantidade(),
                    updated.getProdutoHasItensTipoMedida().get(i).getQuantidade());

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getValorUnitario(),
                    updated.getProdutoHasItensTipoMedida().get(i).getValorUnitario());

            assertEquals(found.getProdutoHasItensTipoMedida().get(0).getItensTipoMedida().getValor(),
                    updated.getProdutoHasItensTipoMedida().get(0).getItensTipoMedida().getValor());

        }

    }

    @Test
    public void alterar_produto_Marca() {

        Marca marcaToUpdate = new Marca();
        marcaToUpdate.setNome(NOME + "_update");
        marcaToUpdate.setDescricao(DESCRICAO + "_update");
        marcaToUpdate = marcaService.incluir(marcaToUpdate);

        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setMarca(marcaToUpdate);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);

        assertMarca(updated.getMarca(), marcaToUpdate);

        assertNotEquals(updated.getMarca().getCodigo(), marca.getCodigo());
        assertNotEquals(updated.getMarca().getNome(), marca.getNome());
        assertNotEquals(updated.getMarca().getDescricao(), marca.getDescricao());

    }

    @Test
    public void alterar_produto_Categoria() {
        // TODO:
    }

    @Test
    public void alterar_produto_SubCategoria() {
        // TODO:
    }

    @Test
    public void alterar_produto_Fornecedor() {

        Fornecedor fornecedorToUpdate = new Fornecedor();
        fornecedorToUpdate.setNome(NOME + "_update");
        fornecedorToUpdate.setDescricao(DESCRICAO + "_update");
        fornecedorToUpdate = fornecedorService.incluir(fornecedorToUpdate);

        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setFornecedor(fornecedorToUpdate);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);

        assertFornecedor(updated.getFornecedor(), fornecedorToUpdate);

        assertNotEquals(updated.getFornecedor().getCodigo(), fornecedor.getCodigo());
        assertNotEquals(updated.getFornecedor().getNome(), fornecedor.getNome());
        assertNotEquals(updated.getFornecedor().getDescricao(), fornecedor.getDescricao());

    }

    @Test
    public void excluir() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        assertNotNull(found);
        produtoService.excluir(found.getCodigo());
    }


    @Test
    public void consultarByCodigo() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), produto.getCodigo());
    }

    @Test
    public void consultarByBarCode() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByBarCode(produto.getBarCode());
        assertNotNull(found);
        assertEquals(found.getCodigo(), produto.getCodigo());
    }

    @Test
    public void consultar() {
        produto = produtoService.incluir(produto);
        List<Produto> produtos = produtoService.consultar();
        assertNotNull(produtos);
    }
}
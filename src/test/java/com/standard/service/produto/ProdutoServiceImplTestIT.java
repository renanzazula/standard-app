package com.standard.service.produto;

import com.standard.BaseTest;
import com.standard.domain.Produto;
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
import com.standard.service.subCategoria.SubCategoriaService;
import com.standard.service.subCategoria.SubCategoriaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProdutoServiceImplTestIT extends BaseTest {


    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

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
    private MedidaService medidaService;
    private SubCategoriaService subCategoriaService;
    private CategoriaService categoriaService;
    private FornecedorService fornecedorService;
    private DominioService dominioService;
    private ProdutoService produtoService;

    @Before
    public void setUp() throws Exception {

        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository,
                subCategoriaRepository, marcaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subCategoriaService = new SubCategoriaServiceImpl(subCategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subCategoriaRepository);
        fornecedorService = new FornecedorServiceImpl(fornecedorRepository);
        dominioService = new DominioServiceImpl(dominioRepository);
        produtoService = new ProdutoServiceImpl(produtoRepository, medidaRepository,
                dominioRepository, fornecedorRepository,
                categoriaRepository, subCategoriaRepository,
                marcaRepository, itensTipoMedidaRepository);

        // requeridos
        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpFornecedor();
        fornecedor = fornecedorService.incluir(fornecedor);

        setUpSubCategoria();
        subCategoria = subCategoriaService.incluir(subCategoria);

        setUpCategoria();
        categoria.setSubCategorias(new ArrayList<>());
        categoria.getSubCategorias().add(subCategoria);
        categoria = categoriaService.incluir(categoria);

        setUpDominio();
        dominio = dominioService.incluir(dominio);

        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubCategoria(subCategoria);
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
        produto.setSubCategoria(subCategoria);
        produto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);


    }

    @Test
    public void incluir() {
        Produto produtoSave = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produtoSave.getCodigo());

        Assert.assertEquals(found.getCodigo(), produtoSave.getCodigo());
        Assert.assertEquals(found.getBarCode(), produtoSave.getBarCode());
        Assert.assertEquals(found.getNome(), produtoSave.getNome());
        Assert.assertEquals(found.getStatus(), produtoSave.getStatus());
        Assert.assertEquals(found.getDescricao(), produtoSave.getDescricao());
        Assert.assertEquals(found.getPreco(), produtoSave.getPreco());
        Assert.assertEquals(found.getPrecoVenda(), produtoSave.getPrecoVenda());
        Assert.assertEquals(found.getPreco(), produtoSave.getPreco());
        Assert.assertEquals(found.getPrecoCusto(), produtoSave.getPrecoCusto());
        Assert.assertEquals(found.getPrecoOferta(), produtoSave.getPrecoOferta());
        Assert.assertEquals(found.getDesconto(), produtoSave.getDesconto());
        Assert.assertEquals(found.getPeso(), produtoSave.getPeso());
        Assert.assertEquals(found.getPorcentagem(), produtoSave.getPorcentagem());
        Assert.assertEquals(found.getPorcentagemDesconto(), produtoSave.getPorcentagemDesconto());

        assertMarca(found.getMarca(), produtoSave.getMarca());
        assertCategoria(found.getCategoria(), produtoSave.getCategoria());
        assertSubCategoria(found.getSubCategoria(), produtoSave.getSubCategoria());
        assertFornecedor(found.getFornecedor(), produtoSave.getFornecedor());

        // TODO: MEDIDA ITENS MEDIDA ETC...

    }

    @Test
    public void alterar() {
        // TODO:
    }

    @Test
    public void alterar_produto_Marca() {
        // TODO:
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
        // TODO:
    }

    @Test
    public void excluir() {
        // TODO:
    }

    @Test
    public void validarCodigoProduto() {
        // TODO:
    }

    @Test
    public void consultarByCodigo() {
        // TODO:
    }

    @Test
    public void consultarByBarCode() {
        // TODO:
    }

    @Test
    public void consultar() {
        // TODO:
    }
}
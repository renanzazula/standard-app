package com.standard.service.produto;

import com.standard.BaseTest;
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
        found.setPreco(new Double(15));
        found.setPrecoVenda(new Double(15));
        found.setPreco(new Double(15));
        found.setPrecoCusto(new Double(15));
        found.setPrecoOferta(new Double(15));
        found.setDesconto(new Double(15));
        found.setPeso(new Double(15));
        found.setPorcentagem(2);
        found.setPorcentagemDesconto(2);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);
        Assert.assertEquals(found.getCodigo(), updated.getCodigo());
        Assert.assertEquals(found.getBarCode(), updated.getBarCode());
        Assert.assertEquals(found.getNome(), updated.getNome());
        Assert.assertEquals(found.getStatus(), updated.getStatus());
        Assert.assertEquals(found.getDescricao(), updated.getDescricao());
        Assert.assertEquals(found.getPreco(), updated.getPreco());
        Assert.assertEquals(found.getPrecoVenda(), updated.getPrecoVenda());
        Assert.assertEquals(found.getPreco(), updated.getPreco());
        Assert.assertEquals(found.getPrecoCusto(), updated.getPrecoCusto());
        Assert.assertEquals(found.getPrecoOferta(), updated.getPrecoOferta());
        Assert.assertEquals(found.getDesconto(), updated.getDesconto());
        Assert.assertEquals(found.getPeso(), updated.getPeso());
        Assert.assertEquals(found.getPorcentagem(), updated.getPorcentagem());
        Assert.assertEquals(found.getPorcentagemDesconto(), updated.getPorcentagemDesconto());

        assertMarca(found.getMarca(), updated.getMarca());
        assertCategoria(found.getCategoria(), updated.getCategoria());
        assertSubCategoria(found.getSubCategoria(), updated.getSubCategoria());
        assertFornecedor(found.getFornecedor(), updated.getFornecedor());
        assertMarcaSubCategoriaCategoriaValor(found.getMedida());
        Assert.assertEquals(found.getProdutoHasItensTipoMedida().size(), updated.getProdutoHasItensTipoMedida().size());

        for (int i = 0; i < found.getProdutoHasItensTipoMedida().size(); i++) {
            // codigo sempre muda

            Assert.assertEquals(found.getProdutoHasItensTipoMedida().get(i).getDominios().size(),
                    updated.getProdutoHasItensTipoMedida().get(i).getDominios().size());

            for (int j = 0; j < found.getProdutoHasItensTipoMedida().get(i).getDominios().size(); j++) {
                assertDominios(found.getProdutoHasItensTipoMedida().get(i).getDominios().get(j),
                        updated.getProdutoHasItensTipoMedida().get(i).getDominios().get(j));
            }

            Assert.assertEquals(found.getProdutoHasItensTipoMedida().get(i).getQuantidade(),
                                updated.getProdutoHasItensTipoMedida().get(i).getQuantidade());

            Assert.assertEquals(found.getProdutoHasItensTipoMedida().get(i).getValorUnitario(),
                                   updated.getProdutoHasItensTipoMedida().get(i).getValorUnitario());

            asserItensTipoMedida(found.getProdutoHasItensTipoMedida().get(i).getItensTipoMedida(),
                                 updated.getProdutoHasItensTipoMedida().get(i).getItensTipoMedida());

            Assert.assertEquals(found.getProdutoHasItensTipoMedida().get(i).getProduto(),
                                updated.getProdutoHasItensTipoMedida().get(i).getProduto());
        }

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
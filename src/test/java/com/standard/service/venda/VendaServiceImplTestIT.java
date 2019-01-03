package com.standard.service.venda;

import com.standard.BaseTest;
import com.standard.domain.*;
import com.standard.entity.ClienteEntity;
import com.standard.repository.*;
import com.standard.service.caixa.CaixaService;
import com.standard.service.caixa.CaixaServiceImpl;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.categoria.CategoriaServiceImpl;
import com.standard.service.dominio.DominioService;
import com.standard.service.dominio.DominioServiceImpl;
import com.standard.service.formaDePagamento.FormaDePagamentoService;
import com.standard.service.formaDePagamento.FormaDePagamentoServiceImpl;
import com.standard.service.fornecedor.FornecedorService;
import com.standard.service.fornecedor.FornecedorServiceImpl;
import com.standard.service.marca.MarcaService;
import com.standard.service.marca.MarcaServiceImpl;
import com.standard.service.medida.MedidaService;
import com.standard.service.medida.MedidaServiceImpl;
import com.standard.service.produto.ProdutoService;
import com.standard.service.produto.ProdutoServiceImpl;
import com.standard.service.subcategoria.SubCategoriaService;
import com.standard.service.subcategoria.SubCategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendaServiceImplTestIT extends BaseTest {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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

    @Autowired
    private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;


    private MarcaService marcaService;
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;
    private SubCategoriaService subCategoriaService;
    private CategoriaService categoriaService;
    private DominioService dominioService;
    private MedidaService medidaService;
    private VendaService vendaService;
    private CaixaService caixaService;
    private FormaDePagamentoService formaDePagamentoService;

    // Fixme: later
    ClienteEntity clienteEntity = null;

    @BeforeEach
    public void setUp() {

        caixaService =  new CaixaServiceImpl(caixaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subCategoriaService = new SubCategoriaServiceImpl(subCategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subCategoriaRepository);
        fornecedorService = new FornecedorServiceImpl(fornecedorRepository);
        dominioService = new DominioServiceImpl(dominioRepository);
        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository,
                subCategoriaRepository, marcaRepository);

        produtoService = new ProdutoServiceImpl(produtoRepository, medidaRepository,
                dominioRepository, fornecedorRepository,
                categoriaRepository, subCategoriaRepository,
                marcaRepository, itensTipoMedidaRepository);

        vendaService = new VendaServiceImpl(vendaRepository, formaDePagamentoRepository, caixaRepository,
                clienteRepository, produtoHasItensTipoMedidaRepository, caixaService);

        clienteEntity = new ClienteEntity();
        clienteRepository.save(clienteEntity);

        Cliente cliente = new Cliente();
        cliente.setCodigo(clienteEntity.getCodigo());

        formaDePagamentoService  = new FormaDePagamentoServiceImpl(formaDePagamentoRepository);
        setUpFormasDePagamento();
        formasDePagamento = formaDePagamentoService.incluir(formasDePagamento);


        caixa = new Caixa();
        caixa.setValorInicial(5.0);
        caixa = caixaService.abrirCaixa(caixa);

        // requeridos
        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpFornecedor();
        fornecedor = fornecedorService.incluir(fornecedor);

        setUpSubCategoria();
        subCategoria = subCategoriaService.incluir(subCategoria);

        setUpCategoria();
        categoria.setSubcategorias(new ArrayList<>());
        categoria.getSubcategorias().add(subCategoria);
        categoria = categoriaService.incluir(categoria);

        setUpDominio();
        dominio = dominioService.incluir(dominio);

        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategoria(subCategoria);
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
        produto = produtoService.incluir(produto);

        // fim
        venda = new Venda();
        venda.setValorTotal(10.0);
        venda.setQuantidade(10);
        venda.setSubTotal(10.0);
        venda.setValorPendente(10.0);
        venda.setValorPago(10.0);
        venda.setDesconto(10.0);
        venda.setTotalApagar(10.0);
        venda.setTroco(10.0);
        venda.setPagamento(10.0);
        venda.setStatus("");
        venda.setCaixa(new Caixa());
        venda.setCaixa(caixa);
        venda.setFormaDePagamento(formasDePagamento);
        venda.setCliente(cliente);
        VendaHasItemProduto vendaHasItemProduto = new VendaHasItemProduto();

        ProdutoHasItensTipoMedida produtoHasItensTipoMedida = new ProdutoHasItensTipoMedida();
        produtoHasItensTipoMedida.setItensTipoMedida(medida.getItensTipoMedida().get(0));
        produtoHasItensTipoMedida.setProduto(produto);
        produtoHasItensTipoMedida.setQuantidade(QUANTIDADE_PRODUTOS_VENDA);
        produtoHasItensTipoMedida.setValorUnitario(VALOR_UNITARIO);
        vendaHasItemProduto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);

        List<VendaHasItemProduto> vendaHasItensProdutos = new ArrayList<>();
        vendaHasItensProdutos.add(vendaHasItemProduto);
        venda.setVendaHasItemProduto(vendaHasItensProdutos);
    }

    @Test
    public void incluir() {
        vendaService.incluir(venda);
    }

    @Test
    public void alterar() {
    }

    @Test
    public void cancelar() {
    }

    @Test
    public void consultarByCodigo() {
    }

    @Test
    public void consultar() {
        vendaService.consultar();
    }

    @Test
    public void filtrarVenda() {
    }
}
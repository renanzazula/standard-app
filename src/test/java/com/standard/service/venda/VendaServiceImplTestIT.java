package com.standard.service.venda;

import com.standard.BaseTest;
import com.standard.domain.*;
import com.standard.entity.CustomerEntity;
import com.standard.enums.StatusVendaEnum;
import com.standard.repository.*;
import com.standard.service.caixa.PosService;
import com.standard.service.caixa.PosServiceImpl;
import com.standard.service.categoria.CategoryService;
import com.standard.service.categoria.CategoryServiceImpl;
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


@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class VendaServiceImplTestIT extends BaseTest {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    @Autowired
    private PosRepository posRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Autowired
    private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;


    private MarcaService marcaService;
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;
    private SubcategoriaService subcategoriaService;
    private CategoryService categoryService;
    private DominioService dominioService;
    private MedidaService medidaService;
    private VendaService vendaService;
    private PosService posService;
    private FormaDePagamentoService formaDePagamentoService;

    // Fixme: later
    CustomerEntity clienteEntity = null;

    @BeforeEach
    public void setUp() {

        posService =  new PosServiceImpl(posRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subcategoriaService = new SubcategoriaServiceImpl(subcategoriaRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoriaRepository);
        fornecedorService = new FornecedorServiceImpl(fornecedorRepository);
        dominioService = new DominioServiceImpl(dominioRepository);
        medidaService = new MedidaServiceImpl(medidaRepository, categoryRepository,
                subcategoriaRepository, marcaRepository);

        produtoService = new ProdutoServiceImpl(produtoRepository, medidaRepository,
                dominioRepository, fornecedorRepository,
                categoryRepository, subcategoriaRepository,
                marcaRepository, itensTipoMedidaRepository);

        vendaService = new VendaServiceImpl(vendaRepository, formaDePagamentoRepository, posRepository,
                customerRepository, produtoHasItensTipoMedidaRepository, posService);

        clienteEntity = new CustomerEntity();
        customerRepository.save(clienteEntity);

        Customer customer = new Customer();
        customer.setId(clienteEntity.getCodigo());

        formaDePagamentoService  = new FormaDePagamentoServiceImpl(formaDePagamentoRepository);
        setUpFormasDePagamento();
        formasDePagamento = formaDePagamentoService.incluir(formasDePagamento);


        pos = new Pos();
        pos.setValorInicial(5.0);
        pos = posService.openPos(pos);

        // requeridos
        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpFornecedor();
        fornecedor = fornecedorService.incluir(fornecedor);

        setUpSubCategoria();
        subcategory = subcategoriaService.save(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.save(category);

        setUpDominio();
        dominio = dominioService.incluir(dominio);

        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategory(subcategory);
        medida.setCategory(category);
        medida.setMarca(marca);
        medida.setItensTipoMedida(itensTipoMedida);
        medida = medidaService.incluir(medida);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        produto.setMarca(marca);
        produto.setFornecedor(fornecedor);
        produto.setCategory(category);
        produto.setMedida(medida);
        produto.setSubcategory(subcategory);
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
        venda.setStatus(StatusVendaEnum.EFETUDA);
        venda.setPos(new Pos());
        venda.setPos(pos);
        venda.setFormaDePagamento(formasDePagamento);
        venda.setCustomer(customer);
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

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
import com.standard.service.dominio.DomainService;
import com.standard.service.dominio.DomainServiceImpl;
import com.standard.service.formaDePagamento.PaymentMethodService;
import com.standard.service.formaDePagamento.PaymentMethodServiceImpl;
import com.standard.service.fornecedor.ProviderService;
import com.standard.service.fornecedor.ProviderServiceImpl;
import com.standard.service.marca.BrandService;
import com.standard.service.marca.BrandServiceImpl;
import com.standard.service.medida.MeasureService;
import com.standard.service.medida.MeasureServiceImpl;
import com.standard.service.produto.ProdutoService;
import com.standard.service.produto.ProdutoServiceImpl;
import com.standard.service.subcategoria.SubcategoryService;
import com.standard.service.subcategoria.SubcategoryServiceImpl;
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
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PosRepository posRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensTipoMedidaRepository itensTipoMedidaRepository;

    @Autowired
    private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;


    private BrandService brandService;
    private ProviderService providerService;
    private ProdutoService produtoService;
    private SubcategoryService subcategoryService;
    private CategoryService categoryService;
    private DomainService domainService;
    private MeasureService measureService;
    private VendaService vendaService;
    private PosService posService;
    private PaymentMethodService paymentMethodService;

    // Fixme: later
    CustomerEntity clienteEntity = null;

    @BeforeEach
    public void setUp() {

        posService =  new PosServiceImpl(posRepository);

        brandService = new BrandServiceImpl(brandRepository);
        subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);
        providerService = new ProviderServiceImpl(providerRepository);
        domainService = new DomainServiceImpl(domainRepository);
        measureService = new MeasureServiceImpl(measureRepository, categoryRepository,
                subcategoryRepository, brandRepository);

        produtoService = new ProdutoServiceImpl(produtoRepository, measureRepository,
                domainRepository, providerRepository,
                categoryRepository, subcategoryRepository,
                brandRepository, itensTipoMedidaRepository);

        vendaService = new VendaServiceImpl(vendaRepository, paymentMethodRepository, posRepository,
                customerRepository, produtoHasItensTipoMedidaRepository, posService);

        clienteEntity = new CustomerEntity();
        customerRepository.save(clienteEntity);

        Customer customer = new Customer();
        customer.setId(clienteEntity.getId());

        paymentMethodService = new PaymentMethodServiceImpl(paymentMethodRepository);
        setUpFormasDePagamento();
        paymentMethod = paymentMethodService.save(paymentMethod);


        pos = new Pos();
        pos.setValorInicial(5.0);
        pos = posService.openPos(pos);

        // requeridos
        setUpMarca();
        brand = brandService.save(brand);

        setUpFornecedor();
        provider = providerService.save(provider);

        setUpSubCategoria();
        subcategory = subcategoryService.save(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.save(category);

        setUpDominio();
        domain = domainService.save(domain);

        setUpItensTipoMedida();
        setUpMedida();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);
        measure = measureService.save(measure);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        produto.setBrand(brand);
        produto.setProvider(provider);
        produto.setCategory(category);
        produto.setMeasure(measure);
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
        venda.setFormaDePagamento(paymentMethod);
        venda.setCustomer(customer);
        VendaHasItemProduto vendaHasItemProduto = new VendaHasItemProduto();

        ProdutoHasItensTipoMedida produtoHasItensTipoMedida = new ProdutoHasItensTipoMedida();
        produtoHasItensTipoMedida.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(0));
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

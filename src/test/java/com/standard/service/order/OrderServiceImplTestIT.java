package com.standard.service.order;

import com.standard.BaseTest;
import com.standard.domain.*;
import com.standard.entity.CustomerEntity;
import com.standard.enums.OrderStatusEnum;
import com.standard.repository.*;
import com.standard.service.brand.BrandService;
import com.standard.service.brand.BrandServiceImpl;
import com.standard.service.category.CategoryService;
import com.standard.service.category.CategoryServiceImpl;
import com.standard.service.domain.DomainService;
import com.standard.service.domain.DomainServiceImpl;
import com.standard.service.measure.MeasureService;
import com.standard.service.measure.MeasureServiceImpl;
import com.standard.service.paymentmethod.PaymentMethodService;
import com.standard.service.paymentmethod.PaymentMethodServiceImpl;
import com.standard.service.pos.PosService;
import com.standard.service.pos.PosServiceImpl;
import com.standard.service.product.ProductService;
import com.standard.service.product.ProductServiceImpl;
import com.standard.service.provider.ProviderService;
import com.standard.service.provider.ProviderServiceImpl;
import com.standard.service.subcategory.SubcategoryService;
import com.standard.service.subcategory.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
class OrderServiceImplTestIT extends BaseTest {

    // Fixme: later
    CustomerEntity clienteEntity = null;
    @Autowired
    private OrderRepository orderRepository;
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
    private ProductRepository productRepository;
    @Autowired
    private ItemsTypeMeasureRepository itemsTypeMeasureRepository;
    @Autowired
    private ProductHasItemsTypeMeasureRepository productHasItemsTypeMeasureRepository;
    private BrandService brandService;
    private ProviderService providerService;
    private ProductService productService;
    private SubcategoryService subcategoryService;
    private CategoryService categoryService;
    private DomainService domainService;
    private MeasureService measureService;
    private OrderService orderService;
    private PosService posService;
    private PaymentMethodService paymentMethodService;

    @BeforeEach
    void setUp() {

        posService = new PosServiceImpl(posRepository);

        brandService = new BrandServiceImpl(brandRepository);
        subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);
        providerService = new ProviderServiceImpl(providerRepository);
        domainService = new DomainServiceImpl(domainRepository);
        measureService = new MeasureServiceImpl(measureRepository, categoryRepository,
                subcategoryRepository, brandRepository);

        productService = new ProductServiceImpl(brandRepository, domainRepository, productRepository, measureRepository,
                providerRepository, categoryRepository, subcategoryRepository,
                itemsTypeMeasureRepository);

        orderService = new OrderServiceImpl(orderRepository, paymentMethodRepository, posRepository,
                customerRepository, productHasItemsTypeMeasureRepository, posService);

        clienteEntity = new CustomerEntity();
        customerRepository.save(clienteEntity);

        Customer customer = new Customer();
        customer.setId(clienteEntity.getId());

        paymentMethodService = new PaymentMethodServiceImpl(paymentMethodRepository);
        setUpFormasDePagamento();
        paymentMethod = paymentMethodService.create(paymentMethod);


        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = posService.openPos(pos);

        // requeridos
        setUpMarca();
        brand = brandService.create(brand);

        setUpFornecedor();
        provider = providerService.create(provider);

        setUpSubCategoria();
        subcategory = subcategoryService.create(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.create(category);

        setUpDominio();
        domain = domainService.create(domain);

        setUpItensTipoMedida();
        setUpMedida();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);
        measure = measureService.create(measure);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        product.setBrand(brand);
        product.setProvider(provider);
        product.setCategory(category);
        product.setMeasure(measure);
        product.setSubcategory(subcategory);
        product.setProductHasItemsTypeMeasure(this.productHasItemsTypeMeasure);
        product = productService.create(product);

        // fim
        order = new Order();
        order.setTotalAmount(10.0);
        order.setQuantity(10);
        order.setSubTotal(10.0);
        order.setPendingAmount(10.0);
        order.setPaidAmount(10.0);
        order.setDiscount(10.0);
        order.setTotalAmountToPaid(10.0);
        order.setChange(10.0);
        order.setPayment(10.0);
        order.setStatus(OrderStatusEnum.DONE);
        order.setPos(new Pos());
        order.setPos(pos);
        order.setFormaDePagamento(paymentMethod);
        order.setCustomer(customer);
        OrderHasItemProduct orderHasItemProduct = new OrderHasItemProduct();

        ProductHasItemsTypeMeasure productHasItemsTypeMeasure = new ProductHasItemsTypeMeasure();
        productHasItemsTypeMeasure.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(0));
        productHasItemsTypeMeasure.setProduct(product);
        productHasItemsTypeMeasure.setQuantity(QUANTIDADE_PRODUTOS_VENDA);
        productHasItemsTypeMeasure.setUnitValue(VALOR_UNITARIO);
        orderHasItemProduct.setProductHasItemsTypeMeasure(productHasItemsTypeMeasure);

        List<OrderHasItemProduct> orderHasItemProducts = new ArrayList<>();
        orderHasItemProducts.add(orderHasItemProduct);
        order.setOrderHasItemProduct(orderHasItemProducts);
    }

}

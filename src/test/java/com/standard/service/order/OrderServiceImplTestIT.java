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
    CustomerEntity customerEntity = null;
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

	private OrderService orderService;

	@BeforeEach
    void setUp() {

		PosService posService = new PosServiceImpl(posRepository);

		BrandService brandService = new BrandServiceImpl(brandRepository);
		SubcategoryService subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
		CategoryService categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);
		ProviderService providerService = new ProviderServiceImpl(providerRepository);
		DomainService domainService = new DomainServiceImpl(domainRepository);
		MeasureService measureService = new MeasureServiceImpl(measureRepository, categoryRepository, subcategoryRepository, brandRepository);

		ProductService productService = new ProductServiceImpl(brandRepository, domainRepository, productRepository, measureRepository, providerRepository, categoryRepository,
				subcategoryRepository, itemsTypeMeasureRepository);

        orderService = new OrderServiceImpl(orderRepository, paymentMethodRepository, posRepository,
                customerRepository, productHasItemsTypeMeasureRepository, posService);

        customerEntity = new CustomerEntity();
        customerRepository.save(customerEntity);

        Customer customer = new Customer();
        customer.setId(customerEntity.getId());

		PaymentMethodService paymentMethodService = new PaymentMethodServiceImpl(paymentMethodRepository);
        setUpPaymentMethod();
        paymentMethod = paymentMethodService.create(paymentMethod);


        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = posService.openPos(pos);

        // required
        setUpBrand();
        brand = brandService.create(brand);

        setUpProvider();
        provider = providerService.create(provider);

        setUpSubcategory();
        subcategory = subcategoryService.create(subcategory);

        setUpCategory();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.create(category);

        setUpDomain();
        domain = domainService.create(domain);

        setUpItemsTypeMeasure();
        setUpMeasure();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);
        measure = measureService.create(measure);

        //Quantity, Domain e Item Type Measure
        setUpProductHasItemsTypeMeasure();

        // campos comuns
        setUpProduct();

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
        order.setPaymentMethod(paymentMethod);
        order.setCustomer(customer);
        OrderHasItemProduct orderHasItemProduct = new OrderHasItemProduct();

        ProductHasItemsTypeMeasure productHasItemsTypeMeasure = new ProductHasItemsTypeMeasure();
        productHasItemsTypeMeasure.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(0));
        productHasItemsTypeMeasure.setProduct(product);
        productHasItemsTypeMeasure.setQuantity(QUANTITY_OF_PRODUCTS);
        productHasItemsTypeMeasure.setUnitValue(UNIT_VALUE);
        orderHasItemProduct.setProductHasItemsTypeMeasure(productHasItemsTypeMeasure);

        List<OrderHasItemProduct> orderHasItemProducts = new ArrayList<>();
        orderHasItemProducts.add(orderHasItemProduct);
        order.setOrderHasItemProduct(orderHasItemProducts);
    }

}

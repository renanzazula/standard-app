package com.standard.service.product;

import com.standard.BaseTest;
import com.standard.domain.Brand;
import com.standard.domain.Product;
import com.standard.domain.Provider;
import com.standard.enums.StatusEnum;
import com.standard.repository.*;
import com.standard.service.brand.BrandService;
import com.standard.service.brand.BrandServiceImpl;
import com.standard.service.category.CategoryService;
import com.standard.service.category.CategoryServiceImpl;
import com.standard.service.domain.DomainService;
import com.standard.service.domain.DomainServiceImpl;
import com.standard.service.measure.MeasureService;
import com.standard.service.measure.MeasureServiceImpl;
import com.standard.service.provider.ProviderService;
import com.standard.service.provider.ProviderServiceImpl;
import com.standard.service.subcategory.SubcategoryService;
import com.standard.service.subcategory.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class ProductServiceImplTestIT extends BaseTest {


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

    private BrandService brandService;
    private ProviderService providerService;
    private ProductService productService;

	@BeforeEach
    void setUp() {

        brandService = new BrandServiceImpl(brandRepository);
        providerService = new ProviderServiceImpl(providerRepository);

        SubcategoryService subCategoryService = new SubcategoryServiceImpl(subcategoryRepository);
		CategoryService categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);
		DomainService domainService = new DomainServiceImpl(domainRepository);
		MeasureService measureService = new MeasureServiceImpl(measureRepository, categoryRepository, subcategoryRepository, brandRepository);

        productService = new ProductServiceImpl(brandRepository, domainRepository, productRepository, measureRepository,
                providerRepository, categoryRepository, subcategoryRepository,
                itemsTypeMeasureRepository);

        // requeridos
        setUpBrand();
        brand = brandService.create(brand);

        setUpProvider();
        provider = providerService.create(provider);

        setUpSubcategory();
        subcategory = subCategoryService.create(subcategory);

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

        //quantadade, dominio e item Medida
        setUpProductHasItemsTypeMeasure();

        // campos comuns
        setUpProduct();

        product.setBrand(brand);
        product.setProvider(provider);
        product.setCategory(category);
        product.setMeasure(measure);
        product.setSubcategory(subcategory);
        product.setProductHasItemsTypeMeasure(productHasItemsTypeMeasure);


    }

    @Test
     void create() {
        Product productSave = productService.create(product);
        Product found = productService.getById(productSave.getId());
        assertProduct(found, productSave);
    }

    @Test
     void update() {
        product = productService.create(product);

        Product found = productService.getById(product.getId());
        found.setBarCode(BAR_0_CODE + "_update");
        found.setName(NAME + "_update");
        found.setStatus(StatusEnum.DISABLE);
        found.setDescription(DESCRIPTION + "_update");
        found.setPrice(15d);
        found.setSalePrice(15d);
        found.setPrice(15d);
        found.setCostPrice(15d);
        found.setDiscountPrice(15d);
        found.setDiscount(15d);
        found.setWeight(15d);
        found.setPercent(2);
        found.setDiscountPercent(2);

        Product updated = productService.update(product.getId(), found);
        assertEquals(found.getId(), updated.getId());
        assertEquals(found.getBarCode(), updated.getBarCode());
        assertEquals(found.getName(), updated.getName());
        assertEquals(found.getStatus(), updated.getStatus());
        assertEquals(found.getDescription(), updated.getDescription());
        assertEquals(found.getPrice(), updated.getPrice());
        assertEquals(found.getSalePrice(), updated.getSalePrice());
        assertEquals(found.getPrice(), updated.getPrice());
        assertEquals(found.getCostPrice(), updated.getCostPrice());
        assertEquals(found.getDiscountPrice(), updated.getDiscountPrice());
        assertEquals(found.getDiscount(), updated.getDiscount());
        assertEquals(found.getWeight(), updated.getWeight());
        assertEquals(found.getPercent(), updated.getPercent());
        assertEquals(found.getDiscountPercent(), updated.getDiscountPercent());

        assertBrand(found.getBrand(), updated.getBrand());
        assertCategory(found.getCategory(), updated.getCategory());
        assertSubcategory(found.getSubcategory(), updated.getSubcategory());
        assertProvider(found.getProvider(), updated.getProvider());
        assertBrandSubCategoryCategoryAmount(found.getMeasure());
        assertEquals(found.getProductHasItemsTypeMeasure().size(), updated.getProductHasItemsTypeMeasure().size());

        for (int i = 0; i < found.getProductHasItemsTypeMeasure().size(); i++) {

            assertEquals(found.getProductHasItemsTypeMeasure().get(i).getDomains().size(),
                    updated.getProductHasItemsTypeMeasure().get(i).getDomains().size());

            for (int j = 0; j < found.getProductHasItemsTypeMeasure().get(i).getDomains().size(); j++) {
                assertDomain(found.getProductHasItemsTypeMeasure().get(i).getDomains().get(j),
                        updated.getProductHasItemsTypeMeasure().get(i).getDomains().get(j));
            }

            assertEquals(found.getProductHasItemsTypeMeasure().get(i).getQuantity(),
                    updated.getProductHasItemsTypeMeasure().get(i).getQuantity());

            assertEquals(found.getProductHasItemsTypeMeasure().get(i).getUnitValue(),
                    updated.getProductHasItemsTypeMeasure().get(i).getUnitValue());

            assertEquals(found.getProductHasItemsTypeMeasure().get(0).getItemsTypeMeasure().getAmount(),
                    updated.getProductHasItemsTypeMeasure().get(0).getItemsTypeMeasure().getAmount());

        }

    }

    @Test
     void updateProductAndBrand() {

        Brand brandToUpdate = new Brand();
        brandToUpdate.setName(NAME + "_update");
        brandToUpdate.setDescription(DESCRIPTION + "_update");
        brandToUpdate = brandService.create(brandToUpdate);

        product = productService.create(product);

        Product found = productService.getById(product.getId());
        found.setBrand(brandToUpdate);

        Product updated = productService.update(product.getId(), found);

        assertBrand(updated.getBrand(), brandToUpdate);

        assertNotEquals(updated.getBrand().getId(), brand.getId());
        assertNotEquals(updated.getBrand().getName(), brand.getName());
        assertNotEquals(updated.getBrand().getDescription(), brand.getDescription());

    }

    @Test
     void updateProductCategory() {
        // TODO:
    }

    @Test
     void updateProductSubCategory() {
        // TODO:
    }

    @Test
     void updateProductProvide() {

        Provider providerToUpdate = new Provider();
        providerToUpdate.setName(NAME + "_update");
        providerToUpdate.setDescription(DESCRIPTION + "_update");
        providerToUpdate = providerService.create(providerToUpdate);

        product = productService.create(product);

        Product found = productService.getById(product.getId());
        found.setProvider(providerToUpdate);

        Product updated = productService.update(product.getId(), found);

        assertProvider(updated.getProvider(), providerToUpdate);

        assertNotEquals(updated.getProvider().getId(), provider.getId());
        assertNotEquals(updated.getProvider().getName(), provider.getName());
        assertNotEquals(updated.getProvider().getDescription(), provider.getDescription());

    }

    @Test
     void delete() {
        product = productService.create(product);
        Product toDelete = productService.getById(product.getId());
        assertNotNull(toDelete);
        productService.delete(toDelete.getId());

        Product found = productService.getById(product.getId());
        assertEquals(found.getStatus(), StatusEnum.DISABLE);
    }


    @Test
     void getById() {
        product = productService.create(product);
        Product found = productService.getById(product.getId());
        assertNotNull(found);
        assertEquals(found.getId(), product.getId());
    }

    @Test
     void getByBarCode() {
        product = productService.create(product);
        Product found = productService.getByBarCode(product.getBarCode());
        assertNotNull(found);
        assertEquals(found.getId(), product.getId());
    }

    @Test
     void findAll() {
        product = productService.create(product);
        List<Product> products = productService.findAll();
        assertNotNull(products);
    }
}

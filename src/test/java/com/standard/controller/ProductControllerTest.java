package com.standard.controller;

import com.standard.domain.Product;
import com.standard.service.brand.BrandService;
import com.standard.service.category.CategoryService;
import com.standard.service.domain.DomainService;
import com.standard.service.measure.MeasureService;
import com.standard.service.product.ProductService;
import com.standard.service.provider.ProviderService;
import com.standard.service.subcategory.SubcategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private MeasureService measureService;

    @MockitoBean
    private BrandService brandService;

    @MockitoBean
    private ProviderService providerService;


    @MockitoBean
    private SubcategoryService subcategoryService;


    @MockitoBean
    private DomainService domainService;

    @BeforeEach
    public void setUp() {


        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        // requeridos
        setUpBrand();
        when(brandService.create(brand)).thenReturn(brand);

        setUpProvider();
        when(providerService.create(provider)).thenReturn(provider);

        setUpSubcategory();
        when(subcategoryService.create(subcategory)).thenReturn(subcategory);

        setUpCategory();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        when(categoryService.create(category)).thenReturn(category);

        setUpDomain();
        when(domainService.create(domain)).thenReturn(domain);

        setUpItemsTypeMeasure();
        setUpMeasure();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasureList);
        when(measureService.create(measure)).thenReturn(measure);

        setUpProductHasItemsTypeMeasure();
        setUpProduct();

        product.setBrand(brand);
        product.setProvider(provider);
        product.setCategory(category);
        product.setMeasure(measure);
        product.setSubcategory(subcategory);
        product.setProductHasItemsTypeMeasure(productHasItemsTypeMeasure);
    }

    @Test
    void testFindAll() throws Exception {
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("bob");

        List<Product> products = Arrays.asList(product, product2);

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get(ProductController.BASE_URL)
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtProductRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo(NAME)))
				.andExpect(jsonPath("$[0].description", equalTo(DESCRIPTION)))
				.andExpect(jsonPath("$[0].barCode", equalTo("0000000BAR0CODE")))
				.andExpect(jsonPath("$[0].name", equalTo("name")))
				.andExpect(jsonPath("$[0].description", equalTo("description")))
				.andExpect(jsonPath("$[0].status", equalTo("ENABLE")))
				.andExpect(jsonPath("$[0].price", equalTo(10.0)))
				.andExpect(jsonPath("$[0].salePrice", equalTo(10.0)))
				.andExpect(jsonPath("$[0].costPrice", equalTo(10.0)))
				.andExpect(jsonPath("$[0].discountPrice", equalTo(10.0)))
				.andExpect(jsonPath("$[0].discount", equalTo(10.0)))
				.andExpect(jsonPath("$[0].weight", equalTo(10.0)))
				.andExpect(jsonPath("$[0].percent", equalTo(1)))
				.andExpect(jsonPath("$[0].discountPercent", equalTo(1)))
				.andExpect(jsonPath("$[0].totalStockQuantity", equalTo(40)));
    }

    @Test
    void testFindById() throws Exception {
        when(productService.getById(product.getId())).thenReturn(product);
        mockMvc.perform(get(ProductController.BASE_URL + "/1")
				.with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtProductRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception
	{
        when(productService.create(product)).thenReturn(product);
        mockMvc.perform(post(ProductController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product))
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtProductRoles())))
                .andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo(NAME)))
				.andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
				.andExpect(jsonPath("$.barCode", equalTo("0000000BAR0CODE")))
				.andExpect(jsonPath("$.name", equalTo("name")))
				.andExpect(jsonPath("$.description", equalTo("description")))
				.andExpect(jsonPath("$.status", equalTo("ENABLE")))
				.andExpect(jsonPath("$.price", equalTo(10.0)))
				.andExpect(jsonPath("$.salePrice", equalTo(10.0)))
				.andExpect(jsonPath("$.costPrice", equalTo(10.0)))
				.andExpect(jsonPath("$.discountPrice", equalTo(10.0)))
				.andExpect(jsonPath("$.discount", equalTo(10.0)))
				.andExpect(jsonPath("$.weight", equalTo(10.0)))
				.andExpect(jsonPath("$.percent", equalTo(1)))
				.andExpect(jsonPath("$.discountPercent", equalTo(1)))
				.andExpect(jsonPath("$.totalStockQuantity", equalTo(40)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(ProductController.BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtProductRoles()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdate() throws Exception
	{
        when(productService.update(1L,product)).thenReturn(product);
        mockMvc.perform(put(ProductController.BASE_URL + "/1")
                .with(jwt().jwt(jwt -> jwt.claim("user", "spring")).authorities(createJwtProductRoles()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME)))
				.andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
				.andExpect(jsonPath("$.barCode", equalTo(BAR_0_CODE)))
				.andExpect(jsonPath("$.status", equalTo("ENABLE")))
				.andExpect(jsonPath("$.price", equalTo(10.0)))
				.andExpect(jsonPath("$.salePrice", equalTo(10.0)))
				.andExpect(jsonPath("$.costPrice", equalTo(10.0)))
				.andExpect(jsonPath("$.discountPrice", equalTo(10.0)))
				.andExpect(jsonPath("$.discount", equalTo(10.0)))
				.andExpect(jsonPath("$.weight", equalTo(10.0)))
				.andExpect(jsonPath("$.percent", equalTo(1)))
				.andExpect(jsonPath("$.discountPercent", equalTo(1)))
				.andExpect(jsonPath("$.totalStockQuantity", equalTo(40)));
    }

}

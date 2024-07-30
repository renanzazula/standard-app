package com.standard.controller;

import com.standard.domain.Product;
import com.standard.service.category.CategoryService;
import com.standard.service.domain.DomainService;
import com.standard.service.provider.ProviderService;
import com.standard.service.brand.BrandService;
import com.standard.service.measure.MeasureService;
import com.standard.service.product.ProductService;
import com.standard.service.subcategory.SubcategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
public class ProductControllerTest extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private MeasureService measureService;

    @MockBean
    private BrandService brandService;

    @MockBean
    private ProviderService providerService;


    @MockBean
    private SubcategoryService subcategoryService;


    @MockBean
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
        measure.setItemsTypeMeasure(itemsTypeMeasure);
        when(measureService.create(measure)).thenReturn(measure);

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
    public void testFindAll() throws Exception {
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("bob");

        List<Product> products = Arrays.asList(product, product2);

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get(ProductController.BASE_URL)
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is(NAME)));
        // Todo: others fields
    }

    @Test
    public void testFindById() throws Exception {
        when(productService.getById(product.getId())).thenReturn(product);
        mockMvc.perform(get(ProductController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    public void testCreate() {
//        when(produtoService.incluir(produto)).thenReturn(produto);
//
//        mockMvc.perform(post(ProdutoController.BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(produto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", equalTo(NOME)))
//                .andExpect(jsonPath("$.description", equalTo(DESCRICAO)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ProductController.BASE_URL + "/1")
                .with(httpBasic("admin", "spring"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() {
//        when(produtoService.alterar(1,medida)).thenReturn(medida);
//        mockMvc.perform(put(ProdutoController.BASE_URL + "/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(medida)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo(NOME)))
//                .andExpect(jsonPath("$.description", equalTo(DESCRICAO)));
    }

    //
    @Test
    public void consultaSubCategoriaByCategoria() {
    }

    @Test
    public void addicionarProduto() {
    }

    @Test
    public void ajaxFindAllItensMedidaByCategoria() {
    }

    @Test
    public void ajaxFindAllItensMedidaByProdutoCodigo() {
    }

    @Test
    public void ajaxFindAllItensMedidaByMedidaCodigo() {
    }

    @Test
    public void ajaxObterDominios() {
    }
}

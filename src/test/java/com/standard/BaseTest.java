package com.standard;

import com.standard.domain.*;
import com.standard.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class BaseTest {


    protected static final String API_KEY = "Api-Key";
    protected static final String API_SECRET = "Api-Secret";
    protected static final String API_KEY_VALUE = "standard";
    protected static final String API_SECRET_VALUE = "standard";


    protected static final String NOME = "nome";
    protected static final String DESCRICAO = "Descriçao";
    protected static final String NOME_UPDATE = "nomeUpdate";
    protected static final String DESCRICAO_UPDATE = "descricaoUpdate";
    protected static final String BAR_0_CODE = "0000000BAR0CODE";
    protected static final int PORCENTAGEM_DESCONTO = 15;
    protected static final int PORCENTAGEM_DESCONTO_UPDATE = 15;
    protected static final int QUANTIDADE_PRODUTOS_VENDA = 1;
    protected static final Double VALOR_UNITARIO = 10.0;

    private static final String VALOR_P = "P";
    private static final String VALOR_X = "X";
    private static final String VALOR_L = "L";
    private static final String VALOR_XL = "XL";
    private static final int QUANTIDADE = 10;
    private static final long CODIGO = 1L;
    
    // obj commons
    protected PaymentMethod paymentMethod = null;
    protected Order order = null;
    protected Pos pos = null;
    protected Brand brand = null;
    protected Subcategory subcategory = null;
    protected Category category = null;
    protected Provider provider = null;
    protected Measure measure = null;
    protected List<ItemsTypeMeasure> itemsTypeMeasure = null;
    protected Domain domain = null;
    protected Product product = null;
    protected List<ProductHasItemsTypeMeasure> productHasItemsTypeMeasure = null;
    protected ItemsTypeMeasure itenTipoMedida = null;
    protected ProductHasItemsTypeMeasure produtoHasItenTipoMedida = null;

    protected void setUpMarca() {
        brand = new Brand();
        brand.setName(NOME);
        brand.setDescription(DESCRICAO);
    }

    protected void setUpSubCategoria() {
        subcategory = new Subcategory();
        subcategory.setName(NOME);
        subcategory.setDescription(DESCRICAO);
    }

    protected void setUpCategoria() {
        category = new Category();
        category.setId(1L);
        category.setName(NOME);
        category.setDescription(DESCRICAO);
    }

    protected void setUpFormasDePagamento(){
        paymentMethod = new PaymentMethod();
        paymentMethod.setName(NOME);
        paymentMethod.setDescription(DESCRICAO);
        paymentMethod.setDiscountPercent(PORCENTAGEM_DESCONTO);
    }

    protected void setUpFornecedor() {
        provider = new Provider();
        provider.setId(1L);
        provider.setName(NOME);
        provider.setDescription(DESCRICAO);
    }

    protected void setUpMedida() {
        measure = new Measure();
        // medida.setCodigo(1l);
        measure.setNome(NOME);
        measure.setDescription(DESCRICAO);
    }

    protected void setUpItensTipoMedida() {
        itemsTypeMeasure = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            itenTipoMedida = new ItemsTypeMeasure();
            switch (i) {
                case 1:
                    itenTipoMedida.setAmount(VALOR_P);
                    break;
                case 2:
                    itenTipoMedida.setAmount(VALOR_L);
                    break;
                case 3:
                    itenTipoMedida.setAmount(VALOR_X);
                    break;
                case 4:
                    itenTipoMedida.setAmount(VALOR_XL);
                    break;
            }
            itemsTypeMeasure.add(itenTipoMedida);
        }
    }

    protected void setUpDominio() {
        domain = new Domain();
        domain.setId(CODIGO);
        domain.setName(NOME);
        domain.setDescription(DESCRICAO);
        domain.setChecked(true);
    }

    protected void setUpProdutoHasItensTipoMedida() {
        productHasItemsTypeMeasure = new ArrayList<>();
        int j = 0;
        for (int i = 1; i < 5; i++) {
            produtoHasItenTipoMedida = new ProductHasItemsTypeMeasure();
            produtoHasItenTipoMedida.setQuantity(QUANTIDADE);
            produtoHasItenTipoMedida.setDomains(new ArrayList<>());
            produtoHasItenTipoMedida.getDomains().add(domain);
            produtoHasItenTipoMedida.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(j));
            productHasItemsTypeMeasure.add(produtoHasItenTipoMedida);
            j++;
        }
    }

    protected void setUpProduto() {
        product = new Product();
        product.setId(1L);
        product.setBarCode(BAR_0_CODE);
        product.setName(NOME);
        product.setStatus(StatusEnum.ATIVO);
        product.setDescription(DESCRICAO);
        product.setPrice(10d);
        product.setSalePrice(10d);
        product.setPrice(10d);
        product.setCostPrice(10d);
        product.setDiscountPrice(10d);
        product.setDiscount(10d);
        product.setWeight(10d);
        product.setPercent(1);
        product.setDiscountPercent(1);
        // fixme: medida.setfoto
    }

    protected void assertMarcaSubCategoriaCategoriaValor(Measure measure) {
        for (int j = 0; j < measure.getItemsTypeMeasure().size(); j++) {

            Brand brandFound = measure.getItemsTypeMeasure().get(j).getBrand();
            assertMarca(brandFound, brand);

            Subcategory subcategoryFound = measure.getItemsTypeMeasure().get(j).getSubcategory();
            assertSubCategoria(subcategoryFound, subcategory);

            Category categoryFound = measure.getItemsTypeMeasure().get(j).getCategory();
            assertCategoria(categoryFound, category);

            assertEquals(measure.getItemsTypeMeasure().get(j).getAmount(), measure.getItemsTypeMeasure().get(j).getAmount());
        }
    }

    protected void assertMarca(Brand expected, Brand found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertCategoria(Category expected, Category found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertSubCategoria(Subcategory expected, Subcategory found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertFornecedor(Provider expected, Provider found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertDominios(Domain expected, Domain found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
        assertEquals(expected.isChecked(), found.isChecked());
    }

    private void assertProdutoHasItensTipoMedida(List<ProductHasItemsTypeMeasure> produto, List<ProductHasItemsTypeMeasure> found) {
        for (int i = 0; i < found.size(); i++) {
            assertEquals(found.get(i).getId(), produto.get(i).getId());
            assertEquals(found.get(i).getDomains().size(),
                    produto.get(i).getDomains().size());

            for (int j = 0; j < found.get(i).getDomains().size(); j++) {
                assertDominios(found.get(i).getDomains().get(j),
                        produto.get(i).getDomains().get(j));
            }
            assertEquals(found.get(i).getQuantity(), produto.get(i).getQuantity());
            assertEquals(found.get(i).getUnitValue(), produto.get(i).getUnitValue());
            assertEquals(found.get(i).getItemsTypeMeasure().getAmount(), produto.get(i).getItemsTypeMeasure().getAmount());

        }
    }

    protected void assertProduto(Product found, Product expected) {
        assertEquals(found.getId(), expected.getId());
        assertEquals(found.getBarCode(), expected.getBarCode());
        assertEquals(found.getName(), expected.getName());
        assertEquals(found.getStatus(), expected.getStatus());
        assertEquals(found.getDescription(), expected.getDescription());
        assertEquals(found.getPrice(), expected.getPrice());
        assertEquals(found.getSalePrice(), expected.getSalePrice());
        assertEquals(found.getPrice(), expected.getPrice());
        assertEquals(found.getCostPrice(), expected.getCostPrice());
        assertEquals(found.getDiscountPrice(), expected.getDiscountPrice());
        assertEquals(found.getDiscount(), expected.getDiscount());
        assertEquals(found.getWeight(), expected.getWeight());
        assertEquals(found.getPercent(), expected.getPercent());
        assertEquals(found.getDiscountPercent(), expected.getDiscountPercent());
        assertMarca(found.getBrand(), expected.getBrand());
        assertCategoria(found.getCategory(), expected.getCategory());
        assertSubCategoria(found.getSubcategory(), expected.getSubcategory());
        assertFornecedor(found.getProvider(), expected.getProvider());
        assertMarcaSubCategoriaCategoriaValor(found.getMeasure());
        assertEquals(found.getProductHasItemsTypeMeasure().size(), expected.getProductHasItemsTypeMeasure().size());
        assertProdutoHasItensTipoMedida(found.getProductHasItemsTypeMeasure(), expected.getProductHasItemsTypeMeasure());
    }

    protected void asserItensTipoMedida(ItemsTypeMeasure found, ItemsTypeMeasure expected) {
        assertEquals(found.getId(), expected.getId());
        assertEquals(found.getAmount(), expected.getAmount());
        assertEquals(found.getMeasure(), expected.getMeasure());
        assertEquals(found.getBrand(), expected.getBrand());
        assertEquals(found.getCategory(), expected.getCategory());
        assertEquals(found.getSubcategory(), expected.getSubcategory());
    }
    
    

}

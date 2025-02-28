package com.standard;

import com.standard.domain.Brand;
import com.standard.domain.Category;
import com.standard.domain.Domain;
import com.standard.domain.ItemsTypeMeasure;
import com.standard.domain.Measure;
import com.standard.domain.Order;
import com.standard.domain.PaymentMethod;
import com.standard.domain.Pos;
import com.standard.domain.Product;
import com.standard.domain.ProductHasItemsTypeMeasure;
import com.standard.domain.Provider;
import com.standard.domain.Subcategory;
import com.standard.enums.StatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BaseTest {

    protected static final long ID = 1L;
    protected static final String NAME = "name";
    protected static final String DESCRIPTION = "description";
    protected static final String NAME_UPDATE = "nameUpdate";
    protected static final String DESCRIPTION_UPDATE = "descriptionUpdate";
    protected static final String BAR_0_CODE = "0000000BAR0CODE";
    protected static final String AMOUNT_P = "P";
    protected static final String AMOUNT_X = "X";
    protected static final String AMOUNT_L = "L";
    protected static final String AMOUNT_XL = "XL";

    protected static final int DISCOUNT_PERCENT = 15;
    protected static final int QUANTITY_OF_PRODUCTS = 1;
    protected static final int QUANTITY = 10;

    protected static final Double UNIT_VALUE = 10.0;

    // obj commons
    protected PaymentMethod paymentMethod = null;
    protected Order order = null;
    protected Pos pos = null;
    protected Brand brand = null;
    protected Subcategory subcategory = null;
    protected Category category = null;
    protected Provider provider = null;
    protected Measure measure = null;
    protected List<ItemsTypeMeasure> itemsTypeMeasureList = null;
    protected Domain domain = null;
    protected Product product = null;
    protected List<ProductHasItemsTypeMeasure> productHasItemsTypeMeasure = null;
    protected ItemsTypeMeasure itemsTypeMeasure = null;
    protected ProductHasItemsTypeMeasure productHasItemTypeMeasure = null;

    protected void setUpBrand() {
        brand = new Brand();
        brand.setName(NAME);
        brand.setDescription(DESCRIPTION);
        brand.setStatus(StatusEnum.ENABLE.name());
    }

    protected void setUpSubcategory() {
        subcategory = new Subcategory();
        subcategory.setName(NAME);
        subcategory.setDescription(DESCRIPTION);
    }

    protected void setUpCategory() {
        category = new Category();
        category.setId(1L);
        category.setName(NAME);
        category.setDescription(DESCRIPTION);
    }

    protected void setUpPaymentMethod(){
        paymentMethod = new PaymentMethod();
        paymentMethod.setName(NAME);
        paymentMethod.setDescription(DESCRIPTION);
        paymentMethod.setDiscountPercent(DISCOUNT_PERCENT);
    }

    protected void setUpProvider() {
        provider = new Provider();
        provider.setId(1L);
        provider.setName(NAME);
        provider.setDescription(DESCRIPTION);
    }

    protected void setUpMeasure() {
        measure = new Measure();
        measure.setName(NAME);
        measure.setDescription(DESCRIPTION);
        measure.setStatus(StatusEnum.ENABLE.name());
    }

    protected void setUpItemsTypeMeasure() {
        itemsTypeMeasureList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            itemsTypeMeasure = new ItemsTypeMeasure();
            switch (i) {
                case 1:
                    itemsTypeMeasure.setAmount(AMOUNT_P);
                    break;
                case 2:
                    itemsTypeMeasure.setAmount(AMOUNT_L);
                    break;
                case 3:
                    itemsTypeMeasure.setAmount(AMOUNT_X);
                    break;
                case 4:
                    itemsTypeMeasure.setAmount(AMOUNT_XL);
                    break;
                default:
                    break;
            }
            itemsTypeMeasureList.add(itemsTypeMeasure);
        }
    }

    protected void setUpDomain() {
        domain = new Domain();
        domain.setId(ID);
        domain.setName(NAME);
        domain.setDescription(DESCRIPTION);
        domain.setChecked(true);
    }

    protected void setUpProductHasItemsTypeMeasure() {
        productHasItemsTypeMeasure = new ArrayList<>();
        int j = 0;
        for (int i = 1; i < 5; i++) {
            productHasItemTypeMeasure = new ProductHasItemsTypeMeasure();
            productHasItemTypeMeasure.setQuantity(QUANTITY);
            productHasItemTypeMeasure.setDomains(new ArrayList<>());
            productHasItemTypeMeasure.getDomains().add(domain);
            productHasItemTypeMeasure.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(j));
            productHasItemsTypeMeasure.add(productHasItemTypeMeasure);
            j++;
        }
    }

    protected void setUpProduct() {
        product = new Product();
        product.setBarCode(BAR_0_CODE);
        product.setName(NAME);
        product.setStatus(StatusEnum.ENABLE.name());
        product.setDescription(DESCRIPTION);
        product.setPrice(10d);
        product.setSalePrice(10d);
        product.setPrice(10d);
        product.setCostPrice(10d);
        product.setDiscountPrice(10d);
        product.setDiscount(10d);
        product.setWeight(10d);
        product.setPercent(1);
        product.setDiscountPercent(1);
        product.setTotalStockQuantity(40);
    }

    protected void assertBrandSubCategoryCategoryAmount(Measure measure) {
        for (int j = 0; j < measure.getItemsTypeMeasure().size(); j++) {

            Brand brandFound = measure.getItemsTypeMeasure().get(j).getBrand();
            assertBrand(brandFound, brand);

            Subcategory subcategoryFound = measure.getItemsTypeMeasure().get(j).getSubcategory();
            assertSubcategory(subcategoryFound, subcategory);

            Category categoryFound = measure.getItemsTypeMeasure().get(j).getCategory();
            assertCategory(categoryFound, category);

            assertEquals(measure.getItemsTypeMeasure().get(j).getAmount(), measure.getItemsTypeMeasure().get(j).getAmount());
        }
    }

    protected void assertBrand(Brand expected, Brand found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertCategory(Category expected, Category found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertSubcategory(Subcategory expected, Subcategory found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertProvider(Provider expected, Provider found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
    }

    protected void assertDomain(Domain expected, Domain found) {
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getDescription(), found.getDescription());
        assertEquals(expected.getChecked(), found.getChecked());
    }

    private void assertProductHasItemsTypeMeasure(List<ProductHasItemsTypeMeasure> product, List<ProductHasItemsTypeMeasure> found) {
        for (int i = 0; i < found.size(); i++) {
            assertEquals(found.get(i).getId(), product.get(i).getId());
            assertEquals(found.get(i).getDomains().size(),
                    product.get(i).getDomains().size());

            for (int j = 0; j < found.get(i).getDomains().size(); j++) {
                assertDomain(found.get(i).getDomains().get(j),
                        product.get(i).getDomains().get(j));
            }
            assertEquals(found.get(i).getQuantity(), product.get(i).getQuantity());
            assertEquals(found.get(i).getUnitValue(), product.get(i).getUnitValue());
            assertEquals(found.get(i).getItemsTypeMeasure().getAmount(), product.get(i).getItemsTypeMeasure().getAmount());

        }
    }

    protected void assertProduct(Product found, Product expected) {
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
        assertBrand(found.getBrand(), expected.getBrand());
        assertCategory(found.getCategory(), expected.getCategory());
        assertSubcategory(found.getSubcategory(), expected.getSubcategory());
        assertProvider(found.getProvider(), expected.getProvider());
        assertBrandSubCategoryCategoryAmount(found.getMeasure());
        assertEquals(found.getProductHasItemsTypeMeasure().size(), expected.getProductHasItemsTypeMeasure().size());
        assertProductHasItemsTypeMeasure(found.getProductHasItemsTypeMeasure(), expected.getProductHasItemsTypeMeasure());
    }

    protected void asserItemsTypeMeasure(ItemsTypeMeasure found, ItemsTypeMeasure expected) {
        assertEquals(found.getId(), expected.getId());
        assertEquals(found.getAmount(), expected.getAmount());
        assertEquals(found.getMeasure(), expected.getMeasure());
        assertEquals(found.getBrand(), expected.getBrand());
        assertEquals(found.getCategory(), expected.getCategory());
        assertEquals(found.getSubcategory(), expected.getSubcategory());
    }

    protected Collection<GrantedAuthority> createJwtBrandRoles()
    {
        return List.of(new SimpleGrantedAuthority("BRAND_SEARCH"), new SimpleGrantedAuthority("BRAND_ADD"),
                new SimpleGrantedAuthority("BRAND_UPDATE"),new SimpleGrantedAuthority("BRAND_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtCategoryRoles()
    {
        return List.of(new SimpleGrantedAuthority("CATEGORY_SEARCH"), new SimpleGrantedAuthority("CATEGORY_ADD"),
                new SimpleGrantedAuthority("CATEGORY_UPDATE"), new SimpleGrantedAuthority("CATEGORY_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtDomainRoles()
    {
        return List.of(new SimpleGrantedAuthority("DOMAIN_SEARCH"), new SimpleGrantedAuthority("DOMAIN_ADD"),
                new SimpleGrantedAuthority("DOMAIN_UPDATE"), new SimpleGrantedAuthority("DOMAIN_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtMeasureRoles()
    {
        return List.of(new SimpleGrantedAuthority("MEASURE_SEARCH"), new SimpleGrantedAuthority("MEASURE_ADD"),
                new SimpleGrantedAuthority("MEASURE_UPDATE"), new SimpleGrantedAuthority("MEASURE_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtPaymentMethodRoles()
    {
        return List.of(new SimpleGrantedAuthority("PAYMENT_METHOD_SEARCH"), new SimpleGrantedAuthority("PAYMENT_METHOD_ADD"),
                new SimpleGrantedAuthority("PAYMENT_METHOD_UPDATE"), new SimpleGrantedAuthority("PAYMENT_METHOD_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtProviderRoles()
    {
        return List.of(new SimpleGrantedAuthority("PROVIDER_SEARCH"), new SimpleGrantedAuthority("PROVIDER_ADD"),
                new SimpleGrantedAuthority("PROVIDER_UPDATE"), new SimpleGrantedAuthority("PROVIDER_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtSubcategoryRoles()
    {
        return List.of(new SimpleGrantedAuthority("SUBCATEGORY_SEARCH"), new SimpleGrantedAuthority("SUBCATEGORY_ADD"),
                new SimpleGrantedAuthority("SUBCATEGORY_UPDATE"), new SimpleGrantedAuthority("SUBCATEGORY_DELETE"));
    }

    protected Collection<GrantedAuthority> createJwtProductRoles()
    {
        return List.of(new SimpleGrantedAuthority("PRODUCT_SEARCH"), new SimpleGrantedAuthority("PRODUCT_ADD"),
                new SimpleGrantedAuthority("PRODUCT_UPDATE"), new SimpleGrantedAuthority("PRODUCT_DELETE"));
    }
}

package com.standard.function;

import com.standard.function.jpa.AuthorityEntityToAuthorityAdapter;
import com.standard.function.jpa.BrandToBrandEntityFunction;
import com.standard.function.jpa.CategoryToCategoryEntityFunction;
import com.standard.function.jpa.CustomerToCustomerEntityFunction;
import com.standard.function.jpa.DomainToDomainEntityFunction;
import com.standard.function.jpa.ItemsTypeMeasureToItemsTypeMeasureEntityFunction;
import com.standard.function.jpa.MeasureToMeasureEntityFunction;
import com.standard.function.jpa.OrderHasItemProductToOrderHasItemProductEntityFunction;
import com.standard.function.jpa.OrderToOrderEntityFunction;
import com.standard.function.jpa.PayBackEntityToPayBackFunction;
import com.standard.function.jpa.PaymentMethodToPaymentMethodEntityFunction;
import com.standard.function.jpa.PosToPosEntityFunction;
import com.standard.function.jpa.ProductHasItemsTypeMeasureToProductHasItemsTypeMeasureEntityFunction;
import com.standard.function.jpa.ProductToProdctEntityFunction;
import com.standard.function.jpa.ProviderToProviderEntityFunction;
import com.standard.function.jpa.SubCategoryToSubCategoryEntityFunction;
import com.standard.function.jpa.UserEntityToUserAdapter;
import com.standard.function.jpa.WithdrawalEntityToWithdrawalFunction;

public class JpaFunctions {

    public JpaFunctions() {
    }

    public static final CategoryToCategoryEntityFunction categoryToCategoryEntity = new CategoryToCategoryEntityFunction();
    public static final SubCategoryToSubCategoryEntityFunction subcategoryToSubCategoryEntity = new SubCategoryToSubCategoryEntityFunction();
    public static final ProviderToProviderEntityFunction providerToProviderEntity = new ProviderToProviderEntityFunction();
    public static final BrandToBrandEntityFunction brandToBrandEntity = new BrandToBrandEntityFunction();
    public static final MeasureToMeasureEntityFunction measureToMeasureEntity = new MeasureToMeasureEntityFunction();
    public static final ItemsTypeMeasureToItemsTypeMeasureEntityFunction itemsTypeMeasureToItemsTypeMeasureEntity = new ItemsTypeMeasureToItemsTypeMeasureEntityFunction();
    public static final ProductToProdctEntityFunction productToProductEntity = new ProductToProdctEntityFunction();
    public static final PaymentMethodToPaymentMethodEntityFunction paymentMethodToPaymentMethodEntity = new PaymentMethodToPaymentMethodEntityFunction();
    public static final ProductHasItemsTypeMeasureToProductHasItemsTypeMeasureEntityFunction productHasItemsTypeMeasureToProductHasItemsTypeMeasureEntity = new ProductHasItemsTypeMeasureToProductHasItemsTypeMeasureEntityFunction();
    public static final DomainToDomainEntityFunction domainToDomainEntity = new DomainToDomainEntityFunction();
    public static final OrderToOrderEntityFunction orderToOrderEntity = new OrderToOrderEntityFunction();
    public static final CustomerToCustomerEntityFunction customerToCustomerEntity = new CustomerToCustomerEntityFunction();
    public static final PosToPosEntityFunction posToPosEntity = new PosToPosEntityFunction();
    public static final OrderHasItemProductToOrderHasItemProductEntityFunction orderHasItemProdutoToOrderHasItemProdutoEntity = new OrderHasItemProductToOrderHasItemProductEntityFunction();
    public static final WithdrawalEntityToWithdrawalFunction withdrawalEntityToWithdrawal = new WithdrawalEntityToWithdrawalFunction();
    public static final PayBackEntityToPayBackFunction payBackEntityToPayBack = new PayBackEntityToPayBackFunction();

    public static final UserEntityToUserAdapter userEntityToUserDtoAdapter = new UserEntityToUserAdapter();
    public static final AuthorityEntityToAuthorityAdapter authorityEntityToAuthorityDtoAdapter = new AuthorityEntityToAuthorityAdapter();

}

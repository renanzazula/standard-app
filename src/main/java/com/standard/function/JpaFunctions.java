package com.standard.function;

import com.standard.function.jpa.*;

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

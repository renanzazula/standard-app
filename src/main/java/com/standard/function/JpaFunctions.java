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
    public static final ItensTipoMedidaToItensTipoMedidaEntityFunction itensTipoMedidaToItensTipoMedidaEntity = new ItensTipoMedidaToItensTipoMedidaEntityFunction();
    public static final ProdutoToProdutoEntityFunction produtoToProdutoEntity = new ProdutoToProdutoEntityFunction();
    public static final PaymentMethodToPaymentMethodEntityFunction paymentMethodToPaymentMethodEntity = new PaymentMethodToPaymentMethodEntityFunction();
    public static final ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity = new ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction();
    public static final DomainToDomainEntityFunction domainToDomainEntity = new DomainToDomainEntityFunction();
    public static final VendaToVendaEntityFunction vendaToVendaEntity = new VendaToVendaEntityFunction();
    public static final CustomerToCustomerEntityFunction customerToCustomerEntity = new CustomerToCustomerEntityFunction();
    public static final PosToPosEntityFunction posToPosEntity = new PosToPosEntityFunction();
    public static final VendaHasItemProdutoToVendaHasItemProdutoEntityFunction vendaHasItemProdutoToVendaHasItemEntity = new VendaHasItemProdutoToVendaHasItemProdutoEntityFunction();
    public static final RetiradaEntityToRetiradaFunction retiradaEntityToRetirada = new RetiradaEntityToRetiradaFunction();
    public static final RecebimentoEntityToRecebimentoFunction recebimentoEntityToRecebimento = new RecebimentoEntityToRecebimentoFunction();

}

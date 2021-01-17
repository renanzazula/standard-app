package com.standard.function;

import com.standard.function.jpa.*;

public class JpaFunctions {

    public JpaFunctions() {
    }

    public static final CategoriaEntityToCategoriaFunction categoriaEntityToCategoria = new CategoriaEntityToCategoriaFunction();
    public static final SubcategoriaEntityToSubcategoriaFunction subCategoriaEntityToSubcategoria = new SubcategoriaEntityToSubcategoriaFunction();
    public static final FornecedorEntityToFornecedorFunction fornecedorEntityToFornecedor = new FornecedorEntityToFornecedorFunction();
    public static final MarcaEntityToMarcaFunction marcaEntityToMarca = new MarcaEntityToMarcaFunction();
    public static final MedidaEntityToMedidaFunction medidaEntityToMedida = new MedidaEntityToMedidaFunction();
    public static final ItensTipoMedidaEntityToItensTipoMedidaFunction itensTipoMedidaEntityToItensTipoMedida = new ItensTipoMedidaEntityToItensTipoMedidaFunction();
    public static final ProdutoEntityToProdutoFunction produtoEntityToProduto = new ProdutoEntityToProdutoFunction();
    public static final FormaDePagamentoEntityToFormaDePagamentoFunction formaDePagamentoEntityToFormasDePagamento = new FormaDePagamentoEntityToFormaDePagamentoFunction();
    public static final ProdutoHasItensTipoMedidaEntityToProdutoHasItensTipoMedidaFunction produtoHasItensTipoMedidaEntityToProdutoHasItensTipoMedida = new ProdutoHasItensTipoMedidaEntityToProdutoHasItensTipoMedidaFunction();
    public static final DominioEntityToDominioFunction dominioEntityToDominio = new DominioEntityToDominioFunction();
    public static final VendaEntityToVendaFunction vendaEntityToVenda = new VendaEntityToVendaFunction();
    public static final ClienteEntityToClienteFunction clienteEntityToCliente = new ClienteEntityToClienteFunction();
    public static final CaixaEntityToCaixaFunction caixaEntityToCaixa = new CaixaEntityToCaixaFunction();
    public static final VendaHasItemProdutoEntityToVendaHasItemProdutoFunction vendaHasItemProdutoEntityToVendaHasItemProduto = new VendaHasItemProdutoEntityToVendaHasItemProdutoFunction();
    public static final RetiradaEntityToRetiradaFunction retiradaEntityToRetirada = new RetiradaEntityToRetiradaFunction();
    public static final RecebimentoEntityToRecebimentoFunction recebimentoEntityToRecebimento = new RecebimentoEntityToRecebimentoFunction();

}

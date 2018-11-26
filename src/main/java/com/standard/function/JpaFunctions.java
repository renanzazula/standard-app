package com.standard.function;

import com.standard.function.jpa.*;

public class JpaFunctions {

	public JpaFunctions() {

	}

	public static final CategoriaToCategoriaEntityFunction categoriaToCategoriaEntity = new CategoriaToCategoriaEntityFunction();
	public static final SubCategoriaToSubCategoriaEntityFunction subCategoriaToSubCategoriaEntity = new SubCategoriaToSubCategoriaEntityFunction();
	public static final FornecedorToFornecedorEntityFunction fornecedortoFornecedorEntity = new FornecedorToFornecedorEntityFunction();
	public static final MarcaToMarcaEntityFunction marcaToMarcaEntity = new MarcaToMarcaEntityFunction();
	public static final MedidaToMedidaEntityFunction medidaToMedidaEntity = new MedidaToMedidaEntityFunction();
	public static final ItensTipoMedidaToItensTipoMedidaEntityFunction itensTipoMedidaToItensTipoMedidaEntity = new ItensTipoMedidaToItensTipoMedidaEntityFunction();
	public static final ProdutoToProdutoEntityFunction produtoToProdutoEntity = new ProdutoToProdutoEntityFunction();
	public static final FormaDePagamentoToFormaDePagamentoEntityFunction formasDePagamentoToFormaDePagamentoEntity = new FormaDePagamentoToFormaDePagamentoEntityFunction();
	public static final ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity = new ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction();
	public static final DominioToDominioEntityFunction dominioToDominioEntity = new DominioToDominioEntityFunction();
	public static final VendaToVendaEntityFunction vendaToVendaEntity = new VendaToVendaEntityFunction();
	public static final ClienteToClienteEntityFunction clienteToClienteEntity = new ClienteToClienteEntityFunction();
	public static final CaixaToCaixaEntityFunction caixaToCaixaEntity = new CaixaToCaixaEntityFunction();
	public static final VendaHasItemProdutoToVendaHasItemProdutoEntityFunction vendaHasItemProdutoToVendaHasItemEntity = new VendaHasItemProdutoToVendaHasItemProdutoEntityFunction();
	public static final RetiradaEntityToRetiradaFunction retiradaEntityToRetirada = new RetiradaEntityToRetiradaFunction();
}

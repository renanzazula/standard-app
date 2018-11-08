package com.standard.service.venda;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.standard.entity.CaixaEntity;
import com.standard.entity.FormaDePagamentoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.entity.VendaEntity;
import com.standard.entity.VendaHasItemProdutoEntity;
import com.standard.enums.StatusVendaEnum;
import com.standard.function.JpaFunctions;
import com.standard.domain.Venda;
import com.standard.domain.VendaHasItemProduto;
import com.standard.repository.CaixaRepository;
import com.standard.repository.ClienteRepository;
import com.standard.repository.FormaDePagamentoRepository;
import com.standard.repository.ProdutoHasItensTipoMedidaRepository;
import com.standard.repository.VendaRepository;
import com.standard.service.caixa.CaixaService;

@Service
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;

	@Autowired
	private CaixaService caixaService;
	
	@Override
	@Transactional
	public Venda incluir(Venda venda) {
		
		Integer quantidadeTotalEstoque = 0;		
		VendaEntity vendaDB = new VendaEntity();

		// itens medida
		Set<VendaHasItemProdutoEntity> vendaHasItemProdutoSet = new HashSet<>();
		for (VendaHasItemProduto itemVenda :venda.getVendaHasItemProduto()) {
			VendaHasItemProdutoEntity vendaHasItemProduto = new VendaHasItemProdutoEntity();
 
			Integer codigo = getProdutoHasItensTipoMedida(
					itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(),
					itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
			
			ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
			quantidadeTotalEstoque = (quantidadeTotalEstoque + itemVenda.getProdutoHasItensTipoMedida().getQuantidade()); 
			vendaHasItemProduto.setQuantidade(itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
			vendaHasItemProduto.setValorUnitario(itemVenda.getProdutoHasItensTipoMedida().getValorUnitario());
			vendaHasItemProduto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);
			vendaHasItemProduto.setVenda(vendaDB);
			vendaHasItemProdutoSet.add(vendaHasItemProduto);
		}
		
		vendaDB.setVendaHasItemProduto(vendaHasItemProdutoSet);
		vendaDB.setQuantidade(quantidadeTotalEstoque);
		vendaDB.setSubTotal(venda.getSubTotal());
		vendaDB.setValorPendente(venda.getValorPendente());
		vendaDB.setValorPago(venda.getValorPago());
		vendaDB.setDesconto(venda.getDesconto());
		vendaDB.setTotalApagar(venda.getTotalApagar());
		vendaDB.setTroco(venda.getTroco());
		vendaDB.setPagamento(venda.getPagamento());
		vendaDB.setValorTotal(venda.getSubTotal()); // posso considerar valor total é sub total venda... TODO: validar
		vendaDB.setFormaDePagamento(formaDePagamentoRepository.getOne(venda.getFormaDePagamento().getCodigo()));
		vendaDB.setCliente(clienteRepository.getOne(1));

		CaixaEntity caixa = caixaRepository.buscarUltimoCaixa();
		vendaDB.setCaixa(caixa);
		
		Venda vResult = null;
//		FIXME: 
		if(caixa != null) {
			if(caixa.getStatus().name().equals("A")) {
				// if caixa satus F error 
				vendaDB.setStatus(StatusVendaEnum.Efetuda.name());
				vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
		
				/**
				 * Update valor total caixa
				 */
				caixaService.updateValorCaixa(caixa, venda);
				
				/**
				 * Efetuar baixa no estoque...		
				 */
				removerProdutoDoEstoque(venda);
			} 
		}
		return vResult;
	}

	/**
	 * Remove quantidade tabela produto_has_itens_tipo_medida 
	 * 
	 * produto_has_itens_tipo_medida
	 * 
	 * @param produto_has_itens_tipo_medida_codigo
	 */
	@Transactional
	private void removerProdutoDoEstoque(Venda venda) {
		venda.getVendaHasItemProduto().forEach(itemVenda -> {
 			Integer codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
			ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
			produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() - itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
			produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
		});
	}
	
	
	
	/**
	 * adiciona quantidade tabela produto_has_itens_tipo_medida 
	 * 
	 * produto_has_itens_tipo_medida
	 * 
	 * @param produto_has_itens_tipo_medida_codigo
	 */
	@Transactional
	private void adicionarProdutoNoEstoque(Venda venda) {
		venda.getVendaHasItemProduto().forEach(itemVenda -> {
 			Integer codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
			ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
			produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() + itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
			produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
		});
	}
	
	@Transactional(readOnly = true)
	private Integer getProdutoHasItensTipoMedida(Integer itemTipoMedidaCodigo, Integer produtoCodigo) {
		return produtoHasItensTipoMedidaRepository
				.findByItensTipoMedidaCodigoAndProdutoCodigo(itemTipoMedidaCodigo, produtoCodigo).getCodigo();
	}

	//Por questao de processo alarar venda 
	// Uma venda devera ser cancelada e crear uma nova... 
	// TODO: Alterar venda nao existe....	
	@Override
	public Venda alterar(Venda venda) {
		VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());

		// itens mideida

		// status; Definicao o que sera status...
		
		vendaDB.setQuantidade(venda.getQuantidade());
		vendaDB.setSubTotal(venda.getSubTotal());
		vendaDB.setValorPendente(venda.getValorPendente());
		vendaDB.setValorPago(venda.getValorPago());
		vendaDB.setDesconto(venda.getDesconto());
		vendaDB.setTotalApagar(venda.getTotalApagar());
		vendaDB.setTroco(venda.getTroco());
		vendaDB.setPagamento(venda.getPagamento());
		vendaDB.setValorTotal(venda.getValorTotal());
		vendaDB.setFormaDePagamento(formaDePagamentoRepository.getOne(venda.getFormaDePagamento().getCodigo()));
		vendaDB.setCliente(clienteRepository.getOne(venda.getCliente().getCodigo()));
		vendaDB.setCaixa(caixaRepository.getOne(venda.getCaixa().getCodigo()));
		return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));

	}

	
	@Override
	@Transactional
	public void cancelar(Venda venda) {
		
		adicionarProdutoNoEstoque(venda);
		
		VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
		
		// TODO: update caixa 
		// forma de pagamento setar como retirada
		//data e hora 
		vendaDB.setStatus(StatusVendaEnum.Cancelado.name());		
		vendaRepository.saveAndFlush(vendaDB);
	}

	@Override
	@Transactional(readOnly = true)
	public Venda consultarByCodigo(Venda venda) {
		return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.getOne(venda.getCodigo()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Venda> consultar() {
		return vendaRepository.findAll().stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Venda> filtrarVenda(Venda venda) {
		VendaEntity vendaEntity = new VendaEntity();
		
		if(venda.getCodigo() != null) {
			vendaEntity.setCodigo(venda.getCodigo());
		}
		
		if(venda.getData() != null) {
			vendaEntity.setData(venda.getData());
		}
		
		if(venda.getStatus() != null) {
			vendaEntity.setStatus(venda.getStatus());
		}
		
//		vendaEntity.setCliente(venda.getCliente());
		
		if(venda.getFormaDePagamento() != null){
			if(venda.getFormaDePagamento().getCodigo() != null) {
				FormaDePagamentoEntity FormaDePagamentoEntity = new FormaDePagamentoEntity();
				FormaDePagamentoEntity.setCodigo(venda.getFormaDePagamento().getCodigo());
				vendaEntity.setFormaDePagamento(FormaDePagamentoEntity);
			}
		}
		return vendaRepository.filter(vendaEntity).stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
	} 
	
}

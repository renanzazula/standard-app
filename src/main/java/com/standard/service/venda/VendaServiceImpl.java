package com.standard.service.venda;

import com.standard.domain.Venda;
import com.standard.domain.VendaHasItemProduto;
import com.standard.entity.*;
import com.standard.enums.StatusVendaEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.*;
import com.standard.service.caixa.CaixaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;
    private final FormaDePagamentoRepository formaDePagamentoRepository;
    private final CaixaRepository caixaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;


    private final CaixaService caixaService;


    public VendaServiceImpl(VendaRepository vendaRepository, FormaDePagamentoRepository
            formaDePagamentoRepository, CaixaRepository caixaRepository,
                            ClienteRepository clienteRepository,
                            ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository, CaixaService caixaService) {
        this.vendaRepository = vendaRepository;
        this.formaDePagamentoRepository = formaDePagamentoRepository;
        this.caixaRepository = caixaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoHasItensTipoMedidaRepository = produtoHasItensTipoMedidaRepository;
        this.caixaService = caixaService;
    }



    @Override
    @Transactional
    public Venda incluir(Venda venda) {

        Integer quantidadeTotalEstoque = 0;
        VendaEntity vendaDB = new VendaEntity();

        // itens medida
        Set<VendaHasItemProdutoEntity> vendaHasItemProdutoSet = new HashSet<>();
        for (VendaHasItemProduto itemVenda : venda.getVendaHasItemProduto()) {
            VendaHasItemProdutoEntity vendaHasItemProduto = new VendaHasItemProdutoEntity();

            Long codigo = getProdutoHasItensTipoMedida(
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
        vendaDB.setCliente(clienteRepository.getOne(venda.getCliente().getCodigo()));

        CaixaEntity caixa = caixaRepository.buscarUltimoCaixa();
        vendaDB.setCaixa(caixa);

        Venda vResult = null;
//		FIXME: 
        if (caixa != null) {
            if (caixa.getStatus().name().equals("A")) {
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
     * <p>
     * produto_has_itens_tipo_medida
     *
     * @param venda
     */
    private void removerProdutoDoEstoque(Venda venda) {
        venda.getVendaHasItemProduto().forEach(itemVenda -> {
            Long codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
            ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
            produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() - itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
            produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
        });
    }


    /**
     * adiciona quantidade tabela produto_has_itens_tipo_medida
     * <p>
     * produto_has_itens_tipo_medida
     *
     * @param venda
     */
    private void adicionarProdutoNoEstoque(Venda venda) {
        venda.getVendaHasItemProduto().forEach(itemVenda -> {
            Long codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
            ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
            produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() + itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
            produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
        });
    }

    private Long getProdutoHasItensTipoMedida(Long itemTipoMedidaCodigo, Long produtoCodigo) {
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

        if (venda.getCodigo() != null) {
            vendaEntity.setCodigo(venda.getCodigo());
        }

        if (venda.getData() != null) {
            vendaEntity.setData(venda.getData());
        }

        if (venda.getStatus() != null) {
            vendaEntity.setStatus(venda.getStatus());
        }
        // TODO:
//		vendaEntity.setCliente(venda.getCliente());

        if (venda.getFormaDePagamento() != null) {
            if (venda.getFormaDePagamento().getCodigo() != null) {
                FormaDePagamentoEntity formaDePagamentoEntity = new FormaDePagamentoEntity();
                formaDePagamentoEntity.setCodigo(venda.getFormaDePagamento().getCodigo());
                vendaEntity.setFormaDePagamento(formaDePagamentoEntity);
            }
        }
        return vendaRepository.filter(vendaEntity).stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
    }

}

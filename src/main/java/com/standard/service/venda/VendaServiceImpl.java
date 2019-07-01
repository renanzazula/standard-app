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

        Integer quantidadeTotalItensVenda = 0;
        VendaEntity vendaDB = new VendaEntity();

        // itens medida
        Set<VendaHasItemProdutoEntity> vendaHasItemProdutoSet = new HashSet<>();
        for (VendaHasItemProduto itemVenda : venda.getVendaHasItemProduto()) {
            VendaHasItemProdutoEntity vendaHasItemProdutoDb = new VendaHasItemProdutoEntity();

            Long codigo = getProdutoHasItensTipoMedida(
                    itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(),
                    itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());

            ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedidaDb = produtoHasItensTipoMedidaRepository.getOne(codigo);
            quantidadeTotalItensVenda = (quantidadeTotalItensVenda + itemVenda.getQuantidade());
            vendaHasItemProdutoDb.setQuantidade(itemVenda.getQuantidade());
            vendaHasItemProdutoDb.setValorUnitario(produtoHasItensTipoMedidaDb.getValorUnitario());
            vendaHasItemProdutoDb.setProdutoHasItensTipoMedida(produtoHasItensTipoMedidaDb);
            vendaHasItemProdutoDb.setVenda(vendaDB);
            vendaHasItemProdutoSet.add(vendaHasItemProdutoDb);
        }

        vendaDB.setVendaHasItemProduto(vendaHasItemProdutoSet);
        vendaDB.setQuantidade(quantidadeTotalItensVenda);
        vendaToVendaDB(venda, vendaDB, venda.getSubTotal());

        CaixaEntity caixa = caixaRepository.buscarUltimoCaixa();
        vendaDB.setCaixa(caixa);

        Venda vResult = null;
//		FIXME: 
        if (caixa != null) {
            if (caixa.getStatus().name().equals("A")) {
                // if caixa satus F error
                vendaDB.setStatus(StatusVendaEnum.PENDENDE_CONFIRMAR);
                vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
            } else {
                // todo Error bussines exeption
            }
        } else {
            // TODO; error or status Fechado
        }
        return vResult;
    }

    private void vendaToVendaDB(Venda venda, VendaEntity vendaDB, Double subTotal) {
        vendaDB.setSubTotal(venda.getSubTotal());
        vendaDB.setValorPendente(venda.getValorPendente());
        vendaDB.setValorPago(venda.getValorPago());
        vendaDB.setDesconto(venda.getDesconto());
        vendaDB.setTotalApagar(venda.getTotalApagar());
        vendaDB.setTroco(venda.getTroco());
        vendaDB.setPagamento(venda.getPagamento());
        vendaDB.setValorTotal(subTotal); // posso considerar valor total é sub total venda... TODO: validar
        vendaDB.setFormaDePagamento(formaDePagamentoRepository.getOne(venda.getFormaDePagamento().getCodigo()));
        vendaDB.setCliente(clienteRepository.getOne(Long.valueOf(1))); //venda.getCliente().getCodigo()
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
            produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() - itemVenda.getQuantidade());
            produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
        });
    }


    /**
     * este methodo quando uma pesso quer devolver o produto, a venda sera para status
     * <p>
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
        ProdutoHasItensTipoMedidaEntity ent = produtoHasItensTipoMedidaRepository.findByItensTipoMedidaCodigoAndProdutoCodigo(itemTipoMedidaCodigo, produtoCodigo);
        return ent.getCodigo();
    }

    @Override
    @Transactional
    public Venda alterar(Venda venda) {
        VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        vendaDB.setQuantidade(venda.getQuantidade());
        vendaToVendaDB(venda, vendaDB, venda.getValorTotal());
        vendaDB.setCaixa(caixaRepository.getOne(venda.getCaixa().getCodigo()));
        return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
    }

    @Override
    public Venda alterarStatusVendaParaEfetuada(Venda venda) {
        VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        CaixaEntity caixa = caixaRepository.buscarUltimoCaixa();
        vendaDB.setCaixa(caixa);

        Venda vResult = null;
        if (caixa != null) {
            if (caixa.getStatus().name().equals("A")) {
                // if caixa satus F error
                vendaDB.setStatus(StatusVendaEnum.EFETUDA);
                vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));

                // Update valor total caixa
                caixaService.updateValorCaixa(caixa, venda);

                // Efetuar baixa no estoque...
                removerProdutoDoEstoque(venda);
            }
        }
        return vResult;
    }

    public Venda alterarStatusVendaParaNaoRealizada(Venda venda) {
        VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        CaixaEntity caixa = caixaRepository.buscarUltimoCaixa();
        vendaDB.setCaixa(caixa);
        Venda vResult = null;
        if (caixa != null) {
            if (caixa.getStatus().name().equals("A")) {
                vendaDB.setStatus(StatusVendaEnum.NAO_REALIZADA);
                vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
            }
        }
        return vResult;
    }

    @Override
    @Transactional
    public void cancelar(Venda venda) {
        VendaEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        vendaDB.setStatus(StatusVendaEnum.CANCELADO);
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

        if (venda.getFormaDePagamento() != null && venda.getFormaDePagamento().getCodigo() != null) {
            FormaDePagamentoEntity formaDePagamentoEntity = new FormaDePagamentoEntity();
            formaDePagamentoEntity.setCodigo(venda.getFormaDePagamento().getCodigo());
            vendaEntity.setFormaDePagamento(formaDePagamentoEntity);
        }
        return vendaRepository.filter(vendaEntity).stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
    }

}

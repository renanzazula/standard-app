package com.standard.service.venda;

import com.standard.domain.Venda;
import com.standard.domain.VendaHasItemProduto;
import com.standard.entity.*;
import com.standard.enums.StatusVendaEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.*;
import com.standard.service.caixa.PosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PosRepository posRepository;
    private final CustomerRepository customerRepository;
    private final ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;
    private final PosService posService;

    public VendaServiceImpl(VendaRepository vendaRepository, PaymentMethodRepository
            paymentMethodRepository, PosRepository posRepository,
                            CustomerRepository customerRepository,
                            ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository, PosService posService) {
        this.vendaRepository = vendaRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.posRepository = posRepository;
        this.customerRepository = customerRepository;
        this.produtoHasItensTipoMedidaRepository = produtoHasItensTipoMedidaRepository;
        this.posService = posService;
    }

    @Override
    @Transactional
    public Venda incluir(Venda venda) {

        Integer quantidadeTotalItensVenda = 0;
        OrderEntity vendaDB = new OrderEntity();

        // itens medida
        Set<OrderHasItemProductEntity> vendaHasItemProdutoSet = new HashSet<>();
        for (VendaHasItemProduto itemVenda : venda.getVendaHasItemProduto()) {
            OrderHasItemProductEntity vendaHasItemProdutoDb = new OrderHasItemProductEntity();

            Long codigo = getProdutoHasItensTipoMedida(
                    itemVenda.getProdutoHasItensTipoMedida().getItemsTypeMeasure().getId(),
                    itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());

            ProductHasItemsTypeMeasureEntity produtoHasItensTipoMedidaDb = produtoHasItensTipoMedidaRepository.getOne(codigo);
            quantidadeTotalItensVenda = (quantidadeTotalItensVenda + itemVenda.getQuantidade());
            vendaHasItemProdutoDb.setQuantity(itemVenda.getQuantidade());
            vendaHasItemProdutoDb.setUnitValue(produtoHasItensTipoMedidaDb.getUnitValue());
            vendaHasItemProdutoDb.setProductHasItemsTypeMeasure(produtoHasItensTipoMedidaDb);
            vendaHasItemProdutoDb.setOrder(vendaDB);
            vendaHasItemProdutoSet.add(vendaHasItemProdutoDb);
        }

        vendaDB.setOrderHasItemProduct(vendaHasItemProdutoSet);
        vendaDB.setQuantity(quantidadeTotalItensVenda);
        vendaToVendaDB(venda, vendaDB, venda.getSubTotal());

        PosEntity caixa = posRepository.getLastPos();
        vendaDB.setPos(caixa);

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

    private void vendaToVendaDB(Venda venda, OrderEntity vendaDB, Double subTotal) {
        vendaDB.setSubTotal(venda.getSubTotal());
        vendaDB.setPendingAmount(venda.getValorPendente());
        vendaDB.setPaidAmount(venda.getValorPago());
        vendaDB.setDiscount(venda.getDesconto());
        vendaDB.setTotalAmountToPaid(venda.getTotalApagar());
        vendaDB.setChange(venda.getTroco());
        vendaDB.setPagamento(venda.getPagamento());
        vendaDB.setTotalAmount(subTotal); // posso considerar valor total é sub total venda... TODO: validar
        vendaDB.setPaymentMethod(paymentMethodRepository.getOne(venda.getFormaDePagamento().getCodigo()));
        vendaDB.setCustomer(customerRepository.getOne(Long.valueOf(1))); //venda.getCliente().getCodigo()
    }

    /**
     * Remove quantidade tabela product_has_items_type_measure
     * <p>
     * product_has_items_type_measure
     *
     * @param venda
     */
    private void removerProdutoDoEstoque(Venda venda) {
        venda.getVendaHasItemProduto().forEach(itemVenda -> {
            Long codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItemsTypeMeasure().getId(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
            ProductHasItemsTypeMeasureEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
            produtoHasItensTipoMedida.setQuantity(produtoHasItensTipoMedida.getQuantity() - itemVenda.getQuantidade());
            produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
        });
    }


    /**
     * este methodo quando uma pesso quer devolver o produto, a venda sera para status
     * <p>
     * adiciona quantidade tabela product_has_items_type_measure
     * <p>
     * product_has_items_type_measure
     *
     * @param venda
     */
    private void adicionarProdutoNoEstoque(Venda venda) {
        venda.getVendaHasItemProduto().forEach(itemVenda -> {
            Long codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItemsTypeMeasure().getId(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
            ProductHasItemsTypeMeasureEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
            produtoHasItensTipoMedida.setQuantity(produtoHasItensTipoMedida.getQuantity() + itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
            produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
        });
    }

    private Long getProdutoHasItensTipoMedida(Long itemTipoMedidaCodigo, Long produtoCodigo) {
        ProductHasItemsTypeMeasureEntity ent = produtoHasItensTipoMedidaRepository.findByItensTipoMedidaCodigoAndProdutoCodigo(itemTipoMedidaCodigo, produtoCodigo);
        return ent.getId();
    }

    @Override
    @Transactional
    public Venda alterar(Venda venda) {
        OrderEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        vendaDB.setQuantity(venda.getQuantidade());
        vendaToVendaDB(venda, vendaDB, venda.getValorTotal());
        vendaDB.setPos(posRepository.getOne(venda.getPos().getCodigo()));
        return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
    }

    @Override
    public Venda alterarStatusVendaParaEfetuada(Venda venda) {
        OrderEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        PosEntity caixa = posRepository.getLastPos();
        vendaDB.setPos(caixa);

        Venda vResult = null;
        if (caixa != null) {
            if (caixa.getStatus().name().equals("A")) {
                // if caixa satus F error
                vendaDB.setStatus(StatusVendaEnum.EFETUDA);
                vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));

                // Update valor total caixa
                posService.updateAmountPos(caixa, venda);

                // Efetuar baixa no estoque...
                removerProdutoDoEstoque(venda);
            }
        }
        return vResult;
    }

    public Venda alterarStatusVendaParaNaoRealizada(Venda venda) {
        OrderEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
        PosEntity caixa = posRepository.getLastPos();
        vendaDB.setPos(caixa);
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
        OrderEntity vendaDB = vendaRepository.getOne(venda.getCodigo());
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
        OrderEntity orderEntity = new OrderEntity();

        if (venda.getCodigo() != null) {
            orderEntity.setId(venda.getCodigo());
        }

        if (venda.getData() != null) {
            orderEntity.setCreationDate(venda.getData());
        }

        if (venda.getStatus() != null) {
            orderEntity.setStatus(venda.getStatus());
        }
        // TODO:
		// vendaEntity.setCustomer(venda.getCustomer());

        if (venda.getFormaDePagamento() != null && venda.getFormaDePagamento().getCodigo() != null) {
            PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
            paymentMethodEntity.setId(venda.getFormaDePagamento().getCodigo());
            orderEntity.setPaymentMethod(paymentMethodEntity);
        }
        return vendaRepository.filter(orderEntity).stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
    }

}

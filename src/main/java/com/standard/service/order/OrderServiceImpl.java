package com.standard.service.order;

import com.standard.domain.Order;
import com.standard.domain.OrderHasItemProduct;
import com.standard.entity.OrderEntity;
import com.standard.entity.OrderHasItemProductEntity;
import com.standard.entity.PaymentMethodEntity;
import com.standard.entity.PosEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.enums.OrderStatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CustomerRepository;
import com.standard.repository.OrderRepository;
import com.standard.repository.PaymentMethodRepository;
import com.standard.repository.PosRepository;
import com.standard.repository.ProductHasItemsTypeMeasureRepository;
import com.standard.security.exceptions.CustomerNotFoundException;
import com.standard.security.exceptions.OrderNotFoundException;
import com.standard.security.exceptions.PayBackNotFoundException;
import com.standard.security.exceptions.PaymentMethodNotFoundException;
import com.standard.security.exceptions.PosNotFoundException;
import com.standard.security.exceptions.ProductHasItemsTypeMeasureNotFoundException;
import com.standard.service.pos.PosService;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PosRepository posRepository;
    private final CustomerRepository customerRepository;
    private final ProductHasItemsTypeMeasureRepository productHasItemsTypeMeasureRepository;
    private final PosService posService;

    @Override
    @Transactional
    public Order create(Order order) {

        int totalQuantityItemsOrder = 0;

        OrderEntity orderEntity = new OrderEntity();

        Set<OrderHasItemProductEntity> vendaHasItemProdutoSet = new HashSet<>();
        for (OrderHasItemProduct orderHasItemProduct : order.getOrderHasItemProduct()) {
            OrderHasItemProductEntity orderHasItemProductEntity = new OrderHasItemProductEntity();

            Long id = getByItemsTypeMeasureIdAndProductId(
                    orderHasItemProduct.getProductHasItemsTypeMeasure().getItemsTypeMeasure().getId(),
                    orderHasItemProduct.getProductHasItemsTypeMeasure().getProduct().getId());

            ProductHasItemsTypeMeasureEntity productHasItemsTypeMeasureEntity = productHasItemsTypeMeasureRepository.findById(id).orElseThrow(() -> new ProductHasItemsTypeMeasureNotFoundException(
                   ConstantMessage.PRODUCT_ITEM_TYPE_MEASURE_NOT_FOUND));
            totalQuantityItemsOrder = (totalQuantityItemsOrder + orderHasItemProduct.getQuantidade());
            orderHasItemProductEntity.setQuantity(orderHasItemProduct.getQuantidade());
            orderHasItemProductEntity.setUnitValue(productHasItemsTypeMeasureEntity.getUnitValue());
            orderHasItemProductEntity.setProductHasItemsTypeMeasure(productHasItemsTypeMeasureEntity);
            orderHasItemProductEntity.setOrder(orderEntity);
            vendaHasItemProdutoSet.add(orderHasItemProductEntity);
        }

        orderEntity.setOrderHasItemProduct(vendaHasItemProdutoSet);
        orderEntity.setQuantity(totalQuantityItemsOrder);
        orderToOrderEntity(order, orderEntity, order.getSubTotal());

        PosEntity posEntity = posRepository.getLastPos();
        orderEntity.setPos(posEntity);

        Order vResult = null;
        //		FIXME:
        if (posEntity != null) {
            if (posEntity.getStatus().name().equals("A")) {
                // if posE satus F error
                orderEntity.setStatus(OrderStatusEnum.PENDING_TO_CONFIRM);
                vResult = JpaFunctions.orderToOrderEntity.apply(orderRepository.saveAndFlush(orderEntity));
            } else {
                // todo Error bussines exeption
            }
        } else {
            // TODO; error or status Fechado
        }
        return vResult;
    }

    private void orderToOrderEntity(Order order, OrderEntity orderEntity, Double subTotal) {
        orderEntity.setSubTotal(order.getSubTotal());
        orderEntity.setPendingAmount(order.getPendingAmount());
        orderEntity.setPaidAmount(order.getPaidAmount());
        orderEntity.setDiscount(order.getDiscount());
        orderEntity.setTotalAmountToPaid(order.getTotalAmountToPaid());
        orderEntity.setChanging(order.getChange());
        orderEntity.setPayment(order.getPayment());
        orderEntity.setTotalAmount(subTotal);
        orderEntity.setPaymentMethod(paymentMethodRepository.findById(order.getPaymentMethod().getId()).orElseThrow(() -> new PaymentMethodNotFoundException(ConstantMessage.PAYMENT_METHOD_NOT_FOUND)));
        orderEntity.setCustomer(customerRepository.findById(1L).orElseThrow(() -> new CustomerNotFoundException(ConstantMessage.CUSTOMER_NOT_FOUND)));
    }

    /**
     * Remove quantidade tabela product_has_items_type_measure
     * <p>
     * product_has_items_type_measure
     *
     * @param order
     */
    private void removeProductFromStock(Order order) {
        order.getOrderHasItemProduct().forEach(orderItem -> {
            Long id = getByItemsTypeMeasureIdAndProductId(orderItem.getProductHasItemsTypeMeasure().getItemsTypeMeasure().getId(), orderItem.getProductHasItemsTypeMeasure().getProduct().getId());
            ProductHasItemsTypeMeasureEntity productHasItemsTypeMeasureEntity = productHasItemsTypeMeasureRepository.findById(id).orElseThrow(() -> new ProductHasItemsTypeMeasureNotFoundException(ConstantMessage.PRODUCT_ITEM_TYPE_MEASURE_NOT_FOUND));
            productHasItemsTypeMeasureEntity.setQuantity(productHasItemsTypeMeasureEntity.getQuantity() - orderItem.getQuantidade());
            productHasItemsTypeMeasureRepository.saveAndFlush(productHasItemsTypeMeasureEntity);
        });
    }


    /**
     * este methodo quando uma pesso quer devolver o produto, a venda sera para status
     * <p>
     * adiciona quantidade tabela product_has_items_type_measure
     * <p>
     * product_has_items_type_measure
     *
     * @param order
     */
    private void addProductToStock(Order order) {
        order.getOrderHasItemProduct().forEach(orderItem -> {
            Long id = getByItemsTypeMeasureIdAndProductId(orderItem.getProductHasItemsTypeMeasure().getItemsTypeMeasure().getId(), orderItem.getProductHasItemsTypeMeasure().getProduct().getId());
            ProductHasItemsTypeMeasureEntity productHasItemsTypeMeasureEntity = productHasItemsTypeMeasureRepository.findById(id).orElseThrow(() -> new ProductHasItemsTypeMeasureNotFoundException(ConstantMessage.PRODUCT_ITEM_TYPE_MEASURE_NOT_FOUND));
            productHasItemsTypeMeasureEntity.setQuantity(productHasItemsTypeMeasureEntity.getQuantity() + orderItem.getProductHasItemsTypeMeasure().getQuantity());
            productHasItemsTypeMeasureRepository.saveAndFlush(productHasItemsTypeMeasureEntity);
        });
    }

    private Long getByItemsTypeMeasureIdAndProductId(Long itemsTypeMeasureId, Long productId) {
        ProductHasItemsTypeMeasureEntity ent = productHasItemsTypeMeasureRepository.findByItemsTypeMeasureIdAndProductId(itemsTypeMeasureId, productId);
        return ent.getId();
    }

    @Override
    @Transactional
    public Order update(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new OrderNotFoundException(ConstantMessage.ORDER_NOT_FOUND));
        orderEntity.setQuantity(order.getQuantity());
        orderToOrderEntity(order, orderEntity, order.getTotalAmount());
        orderEntity.setPos(posRepository.findById(order.getPos().getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND)));
        return JpaFunctions.orderToOrderEntity.apply(orderRepository.saveAndFlush(orderEntity));
    }

    @Override
    public Order updateStatusOrder(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new OrderNotFoundException(ConstantMessage.ORDER_NOT_FOUND));
        PosEntity posEntity = posRepository.getLastPos();
        orderEntity.setPos(posEntity);

        Order vResult = null;
        if (posEntity != null && posEntity.getStatus().name().equals("A")) {
                // if caixa satus F error
                orderEntity.setStatus(OrderStatusEnum.DONE);
                vResult = JpaFunctions.orderToOrderEntity.apply(orderRepository.saveAndFlush(orderEntity));

                // Update valor total caixa
                posService.updateAmountPos(posEntity, order);

                // Efetuar baixa no estoque...
                removeProductFromStock(order);
            }

        return vResult;
    }

    public Order updateStatusOrderToPending(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new OrderNotFoundException(ConstantMessage.ORDER_NOT_FOUND));
        PosEntity posEntity = posRepository.getLastPos();
        orderEntity.setPos(posEntity);
        Order vResult = null;
        if (posEntity != null && posEntity.getStatus().name().equals("A")) {
            orderEntity.setStatus(OrderStatusEnum.PENDING);
            vResult = JpaFunctions.orderToOrderEntity.apply(orderRepository.saveAndFlush(orderEntity));
        }
        return vResult;
    }

    @Override
    @Transactional
    public void cancel(Order order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new OrderNotFoundException(ConstantMessage.ORDER_NOT_FOUND));
        orderEntity.setStatus(OrderStatusEnum.CANCEL);
        orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Order order) {
        return JpaFunctions.orderToOrderEntity.apply(orderRepository.findById(order.getId()).orElseThrow(() -> new OrderNotFoundException(ConstantMessage.ORDER_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(JpaFunctions.orderToOrderEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> filterOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        if (order.getId() != null) {
            orderEntity.setId(order.getId());
        }

        if (order.getCreationDate() != null) {
              orderEntity.setCreationDate(order.getCreationDate());
        }

        if (order.getStatus() != null) {
            orderEntity.setStatus(order.getStatus());
        }
        // TODO:
		// vendaEntity.setCustomer(venda.getCustomer());

        if (order.getPaymentMethod() != null && order.getPaymentMethod().getId() != null) {
            PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
            paymentMethodEntity.setId(order.getPaymentMethod().getId());
            orderEntity.setPaymentMethod(paymentMethodEntity);
        }
        return orderRepository.filter(orderEntity).stream().map(JpaFunctions.orderToOrderEntity).toList();
    }

}

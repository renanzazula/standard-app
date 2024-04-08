package com.standard.repository;


import com.standard.entity.PaymentMethodEntity;
import com.standard.entity.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

class VendaSpecification implements Specification<OrderEntity> {

    private final OrderEntity venda;

    public VendaSpecification(OrderEntity venda) {
        super();
        this.venda = venda;
    }

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (venda.getId() != null) {
            p.getExpressions().add(cb.equal(root.get("codigo"), venda.getId()));
        }

        if (venda.getCreationDate() != null) {
            p.getExpressions().add(cb.equal(root.get("data"), venda.getCreationDate()));
        }

        if (venda.getStatus() != null) {
            if(venda.getStatus().name().equals("NONE")){
                p.getExpressions().add(cb.equal(root.get("status"), venda.getStatus()));
            }
        }
        // TODO: Cliente

        if (venda.getPaymentMethod() != null && venda.getPaymentMethod().getId() != null) {
            Join<OrderEntity, PaymentMethodEntity> sq = root.join("paymentMethod");
            p.getExpressions().add(cb.equal(sq.get("codigo"), venda.getPaymentMethod().getId()));
        }
        return p;
    }
}

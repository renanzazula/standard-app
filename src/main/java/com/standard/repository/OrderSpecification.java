package com.standard.repository;

import com.standard.entity.OrderEntity;
import com.standard.entity.PaymentMethodEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

class OrderSpecification implements Specification<OrderEntity> {

    private final OrderEntity order;

    public OrderSpecification(OrderEntity order) {
        super();
        this.order = order;
    }

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (order.getId() != null) {
            p.getExpressions().add(cb.equal(root.get("id"), order.getId()));
        }

        if (order.getCreationDate() != null) {
            p.getExpressions().add(cb.equal(root.get("creationDate"), order.getCreationDate()));
        }

        if (order.getStatus() != null) {
            if(order.getStatus().name().equals("NONE")){
                p.getExpressions().add(cb.equal(root.get("status"), order.getStatus()));
            }
        }
        // TODO: Cliente

        if (order.getPaymentMethod() != null && order.getPaymentMethod().getId() != null) {
            Join<OrderEntity, PaymentMethodEntity> sq = root.join("paymentMethod");
            p.getExpressions().add(cb.equal(sq.get("id"), order.getPaymentMethod().getId()));
        }
        return p;
    }
}

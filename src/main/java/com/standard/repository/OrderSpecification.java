package com.standard.repository;

import com.standard.entity.OrderEntity;
import com.standard.entity.PaymentMethodEntity;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

class OrderSpecification implements Specification<OrderEntity> {

    private final OrderEntity order;

    public OrderSpecification(OrderEntity order) {
        super();
        this.order = order;
    }

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate predicate = cb.conjunction();

        if (order.getId() != null) {
            predicate.getExpressions().add(cb.equal(root.get("id"), order.getId()));
        }

        if (order.getCreationDate() != null) {
            predicate.getExpressions().add(cb.equal(root.get("creationDate"), order.getCreationDate()));
        }

        if (order.getStatus() != null && order.getStatus().name().equals("NONE")) {
                predicate.getExpressions().add(cb.equal(root.get("status"), order.getStatus()));
        }
        // TODO: Cliente

        if (order.getPaymentMethod() != null && order.getPaymentMethod().getId() != null) {
            Join<OrderEntity, PaymentMethodEntity> sq = root.join("paymentMethod");
            predicate.getExpressions().add(cb.equal(sq.get("id"), order.getPaymentMethod().getId()));
        }
        return predicate;
    }
}

package com.standard.repository;



import com.standard.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    default List<OrderEntity> filter(OrderEntity orderEntity) {
        return findAll(new OrderSpecification(orderEntity));
    }

}

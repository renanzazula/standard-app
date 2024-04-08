package com.standard.repository;

import com.standard.entity.OrderHasItemProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaHasItemProdutoRepository extends JpaRepository<OrderHasItemProductEntity, Long> {

}

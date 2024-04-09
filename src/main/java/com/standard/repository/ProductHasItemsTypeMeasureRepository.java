package com.standard.repository;

import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHasItemsTypeMeasureRepository extends JpaRepository<ProductHasItemsTypeMeasureEntity, Long> {
    ProductHasItemsTypeMeasureEntity findByItemsTypeMeasureIdAndProductId(Long itemsTypeMeasureId, Long productId);

}



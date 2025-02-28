package com.standard.repository;

import com.standard.entity.ItemsTypeMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsTypeMeasureRepository extends JpaRepository<ItemsTypeMeasureEntity, Long> {

    ItemsTypeMeasureEntity findByMeasureId(Long measureId);

}

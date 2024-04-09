package com.standard.repository;

import com.standard.entity.CategoryEntity;
import com.standard.entity.BrandEntity;
import com.standard.entity.MeasureEntity;
import com.standard.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureRepository extends JpaRepository<MeasureEntity, Long> {

    List<MeasureEntity> findByItemsTypeMeasureCategoryAndItemsTypeMeasureSubcategoryAndItemsTypeMeasureBrand
            (CategoryEntity category_id, SubcategoryEntity subcategory_id, BrandEntity brand_id);

}

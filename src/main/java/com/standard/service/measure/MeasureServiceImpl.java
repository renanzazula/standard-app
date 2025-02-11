package com.standard.service.measure;

import com.standard.domain.Measure;
import com.standard.domain.Product;
import com.standard.entity.BrandEntity;
import com.standard.entity.CategoryEntity;
import com.standard.entity.ItemsTypeMeasureEntity;
import com.standard.entity.MeasureEntity;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.BrandRepository;
import com.standard.repository.CategoryRepository;
import com.standard.repository.MeasureRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.security.exceptions.BrandNotFoundException;
import com.standard.security.exceptions.CategoryNotFoundException;
import com.standard.security.exceptions.MeasureNotFoundException;
import com.standard.security.exceptions.SubcategoryNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Builder
@AllArgsConstructor
public class MeasureServiceImpl implements MeasureService {

	private final BrandRepository brandRepository;
	private final MeasureRepository measureRepository;
	private final CategoryRepository categoryRepository;
	private final SubcategoryRepository subcategoryRepository;

    @Override
	@Transactional
	public Measure create(Measure measure) {
		MeasureEntity measureDB = new MeasureEntity();
		measureDB.setDescription(measure.getDescription());
		measureDB.setName(measure.getNome());
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itemsTypeMeasureBuildCreate(measure, itensSet);
			measureDB.setItemsTypeMeasure(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(measureDB));
	}

	@Override
	@Transactional
	public Measure update(Long id, Measure measure) {
		MeasureEntity measureDB = measureRepository.findById(id).orElseThrow(() -> new MeasureNotFoundException(ConstantMessage.MEASURE_NOT_FOUND));
		measureDB.setDescription(measure.getDescription());
		measureDB.setName(measure.getNome());
		measureDB.getItemsTypeMeasure().clear();
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itemsTypeMeasureBuildUpdate(measure, itensSet);
			measureDB.getItemsTypeMeasure().addAll(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(measureDB));
	}

	private void itemsTypeMeasureBuildCreate(Measure measure, Set<ItemsTypeMeasureEntity> itensSet) {
		measure.getItemsTypeMeasure().forEach(itemsTypeMeasure -> {
			ItemsTypeMeasureEntity itemsTypeMeasureEntity = new ItemsTypeMeasureEntity();
			itemsTypeMeasureEntity.setCategory(categoryRepository.findById(measure.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND)));
			itemsTypeMeasureEntity.setSubcategory(subcategoryRepository.findById(measure.getSubcategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.MEASURE_NOT_FOUND)));
			if (measure.getBrand() != null) {
				itemsTypeMeasureEntity.setBrand(brandRepository.findById(measure.getBrand().getId()).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND)));
			}
			itemsTypeMeasureEntity.setAmount(itemsTypeMeasure.getAmount());
			itensSet.add(itemsTypeMeasureEntity);
		});
	}

	private void itemsTypeMeasureBuildUpdate(Measure measure, Set<ItemsTypeMeasureEntity> itensSet) {
		measure.getItemsTypeMeasure().forEach(itemsTypeMeasure -> {
			ItemsTypeMeasureEntity itemsTypeMeasureEntity = new ItemsTypeMeasureEntity();
			itemsTypeMeasureEntity.setCategory(categoryRepository.findById(itemsTypeMeasure.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND)));
			itemsTypeMeasureEntity.setSubcategory(subcategoryRepository.findById(itemsTypeMeasure.getSubcategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.MEASURE_NOT_FOUND)));
			if (itemsTypeMeasure.getBrand() != null) {
				itemsTypeMeasureEntity.setBrand(brandRepository.findById(itemsTypeMeasure.getBrand().getId()).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND)));
			}
			itemsTypeMeasureEntity.setAmount(itemsTypeMeasure.getAmount());
			itensSet.add(itemsTypeMeasureEntity);
		});
	}

	@Override
	@Transactional
	public void delete(Long id) {
		MeasureEntity measureDB = measureRepository.findById(id).orElseThrow(() -> new MeasureNotFoundException(ConstantMessage.MEASURE_NOT_FOUND));
		if (measureDB != null){
			measureDB.setStatus(StatusEnum.DISABLE);
		}
        assert measureDB != null;
        measureRepository.save(measureDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "measureListCache", condition = "#showInventoryOnHand == false")
	public List<Measure> findAll() {
		return measureRepository.findAll().stream().map(JpaFunctions.measureToMeasureEntity).toList();
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "measureCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Measure findById(Long id) {
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.findById(id).orElseThrow(() -> new MeasureNotFoundException(ConstantMessage.MEASURE_NOT_FOUND)));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Measure> findByCategorySubcategoryBrand(Product product) {
		BrandEntity brandEntity = null;
		CategoryEntity categoryEntity = null;
		SubcategoryEntity subcategoryEntity = null;

		if (product.getCategory().getSubcategories() != null && product.getSubcategory().getId() != null) {
			subcategoryEntity = subcategoryRepository.findById(product.getSubcategory().getId()).orElseThrow(() -> new SubcategoryNotFoundException(ConstantMessage.SUBCATEGORY_NOT_FOUND));
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			categoryEntity = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND));
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			brandEntity = brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.BRAND_NOT_FOUND));
		}

		return measureRepository
				.findByItemsTypeMeasureCategoryAndItemsTypeMeasureSubcategoryAndItemsTypeMeasureBrand(categoryEntity, subcategoryEntity, brandEntity)
				.stream().map(JpaFunctions.measureToMeasureEntity).toList();

	}
	
	 
}

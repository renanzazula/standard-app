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
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MeasureServiceImpl implements MeasureService {

	private final MeasureRepository measureRepository;
	private final CategoryRepository categoryRepository;
	private final SubcategoryRepository subcategoryRepository;
	private final BrandRepository brandRepository;

    @Override
	@Transactional
	public Measure create(Measure measure) {
		MeasureEntity measureDB = new MeasureEntity();
		measureDB.setDescription(measure.getDescription());
		measureDB.setName(measure.getNome());
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itemsTypeMeasureBuild(measure, itensSet);
			measureDB.setItemsTypeMeasure(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(measureDB));
	}

	@Override
	@Transactional
	public Measure update(Long id, Measure measure) {
		MeasureEntity measureDB = measureRepository.getById(id);
		measureDB.setDescription(measure.getDescription());
		measureDB.setName(measure.getNome());
		measureDB.getItemsTypeMeasure().clear();
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itemsTypeMeasureBuild(measure, itensSet);
			measureDB.getItemsTypeMeasure().addAll(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(measureDB));
	}

	private void itemsTypeMeasureBuild(Measure measure, Set<ItemsTypeMeasureEntity> itensSet) {
		measure.getItemsTypeMeasure().forEach(itensMedida -> {
			ItemsTypeMeasureEntity itemsTypeMeasureEntity = new ItemsTypeMeasureEntity();
			itemsTypeMeasureEntity.setCategory(categoryRepository.getById(measure.getCategory().getId()));
			itemsTypeMeasureEntity.setSubcategory(subcategoryRepository.getById(measure.getSubcategory().getId()));
			if (measure.getBrand() != null) {
				itemsTypeMeasureEntity.setBrand(brandRepository.getById(measure.getBrand().getId()));
			}
			itemsTypeMeasureEntity.setAmount(itensMedida.getAmount());
			itensSet.add(itemsTypeMeasureEntity);
		});
	}

	@Override
	@Transactional
	public void delete(Long id) {
		MeasureEntity measureDB = measureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
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
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Measure> findByCategorySubcategoryBrand(Product product) {
		BrandEntity brandEntity = null;
		CategoryEntity categoryEntity = null;
		SubcategoryEntity subcategoryEntity = null;

		if (product.getCategory().getSubcategories() != null && product.getSubcategory().getId() != null) {
			subcategoryEntity = subcategoryRepository.getById(product.getSubcategory().getId());
		}

		if (product.getCategory() != null && product.getCategory().getId() != null) {
			categoryEntity = categoryRepository.getById(product.getCategory().getId());
		}

		if (product.getBrand() != null && product.getBrand().getId() != null) {
			brandEntity = brandRepository.getById(product.getBrand().getId());
		}

		return measureRepository
				.findByItemsTypeMeasureCategoryAndItemsTypeMeasureSubcategoryAndItemsTypeMeasureBrand(categoryEntity, subcategoryEntity, brandEntity)
				.stream().map(JpaFunctions.measureToMeasureEntity).toList();

	}
	
	 
}

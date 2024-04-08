package com.standard.service.medida;

import com.standard.domain.Measure;
import com.standard.domain.Produto;
import com.standard.entity.*;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoryRepository;
import com.standard.repository.BrandRepository;
import com.standard.repository.MeasureRepository;
import com.standard.repository.SubcategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeasureServiceImpl implements MeasureService {

	private final MeasureRepository measureRepository;
	private final CategoryRepository categoryRepository;
	private final SubcategoryRepository subcategoryRepository;
	private final BrandRepository brandRepository;

    public MeasureServiceImpl(MeasureRepository measureRepository, CategoryRepository categoryRepository,
							  SubcategoryRepository subcategoryRepository, BrandRepository brandRepository) {
        this.measureRepository = measureRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
	@Transactional
	public Measure save(Measure measure) {
		MeasureEntity measureDB = new MeasureEntity();
		measureDB.setDescription(measure.getDescricao());
		measureDB.setNome(measure.getNome());
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itensMedidaBuild(measure, itensSet);
			measureDB.setItensTipoMedida(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(measureDB));
	}

	@Override
	@Transactional
	public Measure update(Long id, Measure measure) {
		MeasureEntity medidaDB = measureRepository.getById(id);
		medidaDB.setDescription(measure.getDescricao());
		medidaDB.setNome(measure.getNome());
		medidaDB.getItensTipoMedida().clear();
		if (measure.getItemsTypeMeasure() != null) {
			Set<ItemsTypeMeasureEntity> itensSet = new HashSet<>();
			itensMedidaBuild(measure, itensSet);
			medidaDB.getItensTipoMedida().addAll(itensSet);
		}
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.saveAndFlush(medidaDB));
	}

	private void itensMedidaBuild(Measure measure, Set<ItemsTypeMeasureEntity> itensSet) {
		measure.getItemsTypeMeasure().forEach(itensMedida -> {
			ItemsTypeMeasureEntity itens = new ItemsTypeMeasureEntity();
			itens.setCategory(categoryRepository.getById(measure.getCategory().getId()));
			itens.setSubcategory(subcategoryRepository.getById(measure.getSubcategory().getCodigo()));
			if (measure.getBrand() != null) {
				itens.setBrand(brandRepository.getById(measure.getBrand().getCodigo()));
			}
			itens.setAmount(itensMedida.getValor());
			itensSet.add(itens);
		});
	}

	@Override
	@Transactional
	public void delete(Long id) {
		MeasureEntity measureDB = measureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if (measureDB != null){
			measureDB.setStatus(StatusEnum.INATIVO);
		}
		measureRepository.save(measureDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "measureListCache", condition = "#showInventoryOnHand == false")
	public List<Measure> findAll() {
		return measureRepository.findAll().stream().map(JpaFunctions.measureToMeasureEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "measureCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Measure findById(Long id) {
		return JpaFunctions.measureToMeasureEntity.apply(measureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Measure> findByCategorySubcategoryBrand(Produto produto) {
		CategoryEntity categoria = null;
		SubcategoryEntity subcategory = null;
		BrandEntity marca = null;
		if (produto.getBrand() != null && produto.getBrand().getCodigo() != null) {
			marca = brandRepository.getById(produto.getBrand().getCodigo());
		}
		if (produto.getCategory().getSubcategories() != null && produto.getSubcategory().getCodigo() != null) {
			subcategory = subcategoryRepository.getById(produto.getSubcategory().getCodigo());
		}
		if (produto.getCategory() != null && produto.getCategory().getId() != null) {
			categoria = categoryRepository.getById(produto.getCategory().getId());
		}
		return measureRepository
				.findByItensTipoMedidaCategoryAndItensTipoMedidaSubcategoryAndItensTipoMedidaBrand(categoria, subcategory, marca)
				.stream().map(JpaFunctions.measureToMeasureEntity).collect(Collectors.toList());

	}
	
	 
}

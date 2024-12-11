package com.standard.service.brand;

import com.standard.domain.Brand;
import com.standard.entity.BrandEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

    @Override
	@Transactional
	public Brand create(Brand entity) {
		BrandEntity brandDB = new BrandEntity();
		brandDB.setDescription(entity.getDescription());
		brandDB.setName(entity.getName());
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.save(brandDB));
	}

	@Override
	@Transactional
	public Brand update(Long id, Brand brand) {
		BrandEntity brandDB = brandRepository.getById(id);
		brandDB.setDescription(brand.getDescription());
		brandDB.setName(brand.getName());
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.saveAndFlush(brandDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		BrandEntity brandDB = brandRepository.getById(id);
		brandDB.setStatus(StatusEnum.DISABLE);
		brandRepository.save(brandDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "brandListCache", condition = "#showInventoryOnHand == false")
	public List<Brand> findAll() {
		return brandRepository.findAll().stream().map(JpaFunctions.brandToBrandEntity).toList();
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "brandCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Brand findById(Long id) {
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}

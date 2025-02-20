package com.standard.service.brand;

import com.standard.domain.Brand;
import com.standard.entity.BrandEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.BrandRepository;
import com.standard.security.exceptions.BrandNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

    @Override
	@Transactional
	public Brand create(Brand brand) {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setDescription(brand.getDescription());
		brandEntity.setName(brand.getName());
		brandEntity.setStatus(StatusEnum.valueOf(brand.getStatus()));
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.save(brandEntity));
	}

	@Override
	@Transactional
	public Brand update(Long id, Brand brand) {
		BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND));
		brandEntity.setDescription(brand.getDescription());
		brandEntity.setName(brand.getName());
		brandEntity.setStatus(StatusEnum.valueOf(brand.getStatus()));
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.saveAndFlush(brandEntity));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		BrandEntity brandDB = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND));
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
		return JpaFunctions.brandToBrandEntity.apply(brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(ConstantMessage.BRAND_NOT_FOUND)));
	}

}

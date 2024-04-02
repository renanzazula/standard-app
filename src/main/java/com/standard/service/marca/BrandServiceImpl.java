package com.standard.service.marca;

import com.standard.domain.Brand;
import com.standard.entity.BrandEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.BrandRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

	private final BrandRepository repository;

    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
	@Transactional
	public Brand save(Brand entity) {
		BrandEntity brandDB = new BrandEntity();
		brandDB.setDescricao(entity.getDescricao());
		brandDB.setNome(entity.getNome());
		return JpaFunctions.brandToBrandEntity.apply(repository.save(brandDB));
	}

	@Override
	@Transactional
	public Brand update(Long id, Brand entity) {
		BrandEntity brandDB = repository.getById(id);
		brandDB.setDescricao(entity.getDescricao());
		brandDB.setNome(entity.getNome());
		return JpaFunctions.brandToBrandEntity.apply(repository.saveAndFlush(brandDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		BrandEntity brandDB = repository.getById(id);
		brandDB.setStatus(StatusEnum.INATIVO);
		repository.save(brandDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "brandListCache", condition = "#showInventoryOnHand == false")
	public List<Brand> findAll() {
		return repository.findAll().stream().map(JpaFunctions.brandToBrandEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "brandCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Brand findById(Long id) {
		return JpaFunctions.brandToBrandEntity.apply(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}

package com.standard.service.provider;

import com.standard.domain.Provider;
import com.standard.entity.ProviderEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.ProviderRepository;
import com.standard.security.exceptions.ProviderNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    @Transactional
    @CacheEvict(cacheNames = "providerListCache", allEntries = true)
    public Provider create(Provider provider) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setDescription(provider.getDescription());
        providerEntity.setName(provider.getName());
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.saveAndFlush(providerEntity));
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "providerListCache", allEntries = true)
    public Provider update(Long id, Provider entity) {
        ProviderEntity providerEntity = providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND));
        providerEntity.setDescription(entity.getDescription());
        providerEntity.setName(entity.getName());
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.saveAndFlush(providerEntity));
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "providerListCache", allEntries = true)
    public void delete(Long id) {
        ProviderEntity providerEntity = providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND));
        providerEntity.setStatus(StatusEnum.DISABLE);
        providerRepository.save(providerEntity);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerListCache")
    public List<Provider> findAll() {
        return providerRepository.findAll().stream().map(JpaFunctions.providerToProviderEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerListCache", key = "#id")
    public Provider findById(Long id) {
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND)));
    }

}

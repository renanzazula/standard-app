package com.standard.service.provider;

import com.standard.domain.Provider;
import com.standard.entity.ProviderEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.ProviderRepository;
import com.standard.security.exceptions.ProviderNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
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
    public Provider create(Provider entity) {
        ProviderEntity providerDB = new ProviderEntity();
        providerDB.setDescription(entity.getDescription());
        providerDB.setName(entity.getName());
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.saveAndFlush(providerDB));
    }

    @Override
    @Transactional
    public Provider update(Long id, Provider entity) {
        ProviderEntity providerDB = providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND));
        providerDB.setDescription(entity.getDescription());
        providerDB.setName(entity.getName());
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.saveAndFlush(providerDB));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProviderEntity providerDB = providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND));
        providerDB.setStatus(StatusEnum.DISABLE);
        providerRepository.save(providerDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerListCache", condition = "#showInventoryOnHand == false")
    public List<Provider> findAll() {
        return providerRepository.findAll().stream().map(JpaFunctions.providerToProviderEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerCache", key = "#id", condition = "#showInventoryOnHand == false")
    public Provider findById(Long id) {
        return JpaFunctions.providerToProviderEntity.apply(providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(ConstantMessage.PROVIDER_NOT_FOUND)));
    }

}

package com.standard.service.fornecedor;

import com.standard.domain.Provider;
import com.standard.entity.ProviderEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.ProviderRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository repository;

    public ProviderServiceImpl(ProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Provider save(Provider entity) {
        ProviderEntity providerDB = new ProviderEntity();
        providerDB.setDescricao(entity.getDescricao());
        providerDB.setNome(entity.getNome());
        return JpaFunctions.providerToProviderEntity.apply(repository.saveAndFlush(providerDB));
    }

    @Override
    @Transactional
    public Provider update(Long id, Provider entity) {
        ProviderEntity providerDB = repository.getOne(id);
        providerDB.setDescricao(entity.getDescricao());
        providerDB.setNome(entity.getNome());
        return JpaFunctions.providerToProviderEntity.apply(repository.saveAndFlush(providerDB));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProviderEntity providerDB = repository.getOne(id);
        providerDB.setStatus(StatusEnum.INATIVO);
        repository.save(providerDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerListCache", condition = "#showInventoryOnHand == false")
    public List<Provider> findAll() {
        return repository.findAll().stream().map(JpaFunctions.providerToProviderEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "providerCache", key = "#id", condition = "#showInventoryOnHand == false")
    public Provider findById(Long id) {
        return JpaFunctions.providerToProviderEntity.apply(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }

}

package com.standard.service.fornecedor;

import com.standard.domain.Fornecedor;
import com.standard.entity.FornecedorEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.FornecedorRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorServiceImpl(FornecedorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Fornecedor incluir(Fornecedor entity) {
        FornecedorEntity fornecedorDB = new FornecedorEntity();
        fornecedorDB.setDescricao(entity.getDescricao());
        fornecedorDB.setNome(entity.getNome());
        return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
    }

    @Override
    @Transactional
    public Fornecedor alterar(Long codigo, Fornecedor entity) {
        FornecedorEntity fornecedorDB = repository.getOne(codigo);
        fornecedorDB.setDescricao(entity.getDescricao());
        fornecedorDB.setNome(entity.getNome());
        return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
    }

    @Override
    @Transactional
    public void excluir(Long codigo) {
        FornecedorEntity fornecedorDB = repository.getOne(codigo);
        fornecedorDB.setStatus(StatusEnum.INATIVO);
        repository.save(fornecedorDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "fornecedorListCache", condition = "#showInventoryOnHand == false")
    public List<Fornecedor> consultar() {
        return repository.findAll().stream().map(JpaFunctions.fornecedortoFornecedorEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "fornecedorCache", key = "#codigo", condition = "#showInventoryOnHand == false")
    public Fornecedor consultarByCodigo(Long codigo) {
        return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }

}

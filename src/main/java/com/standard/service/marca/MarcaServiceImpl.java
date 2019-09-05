package com.standard.service.marca;

import com.standard.domain.Marca;
import com.standard.entity.MarcaEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.MarcaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaServiceImpl implements MarcaService {

	private final MarcaRepository repository;

    public MarcaServiceImpl(MarcaRepository repository) {
        this.repository = repository;
    }

    @Override
	@Transactional
	public Marca incluir(Marca entity) {
		MarcaEntity marcaDB = new MarcaEntity();
		marcaDB.setDescricao(entity.getDescricao());
		marcaDB.setNome(entity.getNome());
		return JpaFunctions.marcaToMarcaEntity.apply(repository.save(marcaDB));
	}

	@Override
	@Transactional
	public Marca alterar(Long codigo, Marca entity) {
		MarcaEntity marcaDB = repository.getOne(codigo);
		marcaDB.setDescricao(entity.getDescricao());
		marcaDB.setNome(entity.getNome());
		return JpaFunctions.marcaToMarcaEntity.apply(repository.saveAndFlush(marcaDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		MarcaEntity marcaDB = repository.getOne(codigo);
		marcaDB.setStatus(StatusEnum.INATIVO);
		repository.save(marcaDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "marcaListCache", condition = "#showInventoryOnHand == false")
	public List<Marca> consultar() {
		return repository.findAll().stream().map(JpaFunctions.marcaToMarcaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "marcaCache", key = "#codigo", condition = "#showInventoryOnHand == false")
	public Marca consultarByCodigo(Long codigo) {
		return JpaFunctions.marcaToMarcaEntity.apply(repository.findById(codigo).orElse(null));
	}

}

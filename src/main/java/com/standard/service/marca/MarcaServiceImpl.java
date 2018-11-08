package com.standard.service.marca;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.standard.entity.MarcaEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.Marca;
import com.standard.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	private MarcaRepository repository;

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
	public Marca alterar(Integer codigo, Marca entity) {
		MarcaEntity marcaDB = repository.getOne(codigo);
		marcaDB.setDescricao(entity.getDescricao());
		marcaDB.setNome(entity.getNome());
		return JpaFunctions.marcaToMarcaEntity.apply(repository.saveAndFlush(marcaDB));
	}

	@Override
	@Transactional
	public void excluir(Integer codigo) {
		MarcaEntity marcaDB = repository.getOne(codigo);
		//TODO: marcaDB.setStatus(Status.Inativo);
		repository.delete(marcaDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Marca> consultar() {
		return repository.findAll().stream().map(JpaFunctions.marcaToMarcaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Marca consultarByCodigo(Integer codigo) {
		return JpaFunctions.marcaToMarcaEntity.apply(repository.getOne(codigo));
	}

}

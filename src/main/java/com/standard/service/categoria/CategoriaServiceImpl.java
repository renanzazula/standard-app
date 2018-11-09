package com.standard.service.categoria;

import com.standard.domain.Categoria;
import com.standard.entity.CategoriaEntity;
import com.standard.entity.SubCategoriaEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.SubCategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository repository;
	private SubCategoriaRepository subCategoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository repository, SubCategoriaRepository subCategoriaRepository) {
		this.repository = repository;
		this.subCategoriaRepository = subCategoriaRepository;
	}

	@Override
	@Transactional
	public Categoria incluir(Categoria categoria) {
		CategoriaEntity categoriaDB = new CategoriaEntity();
		categoriaDB.setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		if(categoria.getSubCategorias() != null) {
			Set<SubCategoriaEntity> subCategoria = new HashSet<>();
			categoria.getSubCategorias().forEach(sub -> {
				subCategoria.add(subCategoriaRepository.getOne(sub.getCodigo()));
			});
			categoriaDB.setSubCategoriasSet(subCategoria);
		}
		return JpaFunctions.categoriaToCategoriaEntity.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional
	public Categoria alterar(Integer codigo, Categoria categoria) {
		CategoriaEntity categoriaDB = repository.findById(codigo).orElse(null);
		categoriaDB.setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		categoriaDB.getSubCategoriasSet().clear();
		Set<SubCategoriaEntity> subCategoria = new HashSet<>();
		categoria.getSubCategorias().forEach(sub -> {
			subCategoria.add(subCategoriaRepository.getOne(sub.getCodigo()));
		});
		categoriaDB.getSubCategoriasSet().addAll(subCategoria);

		return JpaFunctions.categoriaToCategoriaEntity.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria consultarByCodigo(Integer codigo) {
		return JpaFunctions.categoriaToCategoriaEntity.apply(repository.findById(codigo).orElse(null));
	}


	@Override
	@Transactional(readOnly = true)
	public List<Categoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.categoriaToCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void excluir(Integer codigo) {
		CategoriaEntity categoriaDB = repository.getOne(codigo);
		categoriaDB.getSubCategoriasSet().clear();
		repository.saveAndFlush(categoriaDB);
		repository.delete(repository.getOne(codigo));
	}

}

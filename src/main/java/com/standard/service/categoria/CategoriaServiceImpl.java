package com.standard.service.categoria;

import com.standard.domain.Categoria;
import com.standard.entity.CategoriaEntity;
import com.standard.entity.SubcategoriaEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.SubcategoriaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository repository;
	private final SubcategoriaRepository subcategoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository repository, SubcategoriaRepository subcategoriaRepository) {
		this.repository = repository;
		this.subcategoriaRepository = subcategoriaRepository;
	}

	@Override
	@Transactional
	public Categoria incluir(Categoria categoria) {
		CategoriaEntity categoriaDB = new CategoriaEntity();
		categoriaDB.setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		if(categoria.getSubcategorias() != null) {
			Set<SubcategoriaEntity> subcategoriaSet = new HashSet<>();
			categoria.getSubcategorias().forEach(sub -> subcategoriaSet.add(subcategoriaRepository.getOne(sub.getCodigo())));
			categoriaDB.setSubcategoriasSet(subcategoriaSet);
		}
		return JpaFunctions.categoriaEntityToCategoria.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional
	public Categoria alterar(Long codigo, Categoria categoria) {
		CategoriaEntity categoriaDB = repository.findById(codigo).orElse(null);
		Objects.requireNonNull(categoriaDB).setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		categoriaDB.getSubcategoriasSet().clear();
		Set<SubcategoriaEntity> subcategoriaSet = new HashSet<>();
		categoria.getSubcategorias().forEach(sub -> subcategoriaSet.add(subcategoriaRepository.getOne(sub.getCodigo())));
		categoriaDB.getSubcategoriasSet().addAll(subcategoriaSet);

		return JpaFunctions.categoriaEntityToCategoria.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoriaCache", key = "#codigo", condition = "#showInventoryOnHand == false")
	public Categoria consultarByCodigo(Long codigo) {
		return JpaFunctions.categoriaEntityToCategoria.apply(repository.findById(codigo).orElse(null));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoriaListCache", condition = "#showInventoryOnHand == false")
	public List<Categoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.categoriaEntityToCategoria).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		CategoriaEntity categoriaDB = repository.getOne(codigo);
		if(categoriaDB != null) {
			categoriaDB.setStatus(StatusEnum.INATIVO);
		}
		repository.saveAndFlush(categoriaDB);
	}

}

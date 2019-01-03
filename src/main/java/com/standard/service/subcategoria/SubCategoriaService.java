package com.standard.service.subcategoria;

import com.standard.domain.Subcategoria;

import java.util.List;

public interface SubCategoriaService {

	Subcategoria incluir(Subcategoria objc);

	Subcategoria alterar(Long codigo, Subcategoria objc);

	void excluir(Long codigo);
	
	List<Subcategoria> consultar();

	Subcategoria consultarByCodigo(Long codigo);
	
}

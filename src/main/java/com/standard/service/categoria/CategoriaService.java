package com.standard.service.categoria;

import com.standard.domain.Categoria;

import java.util.List;

public interface CategoriaService {

	Categoria incluir(Categoria entity);

	Categoria alterar(Long codigo, Categoria entity);
 
	Categoria consultarByCodigo(Long codigo);
	
	List<Categoria> consultar();

	void excluir(Long codigo);
}

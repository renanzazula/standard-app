package com.standard.service.categoria;

import com.standard.domain.Categoria;

import java.util.List;

public interface CategoriaService {

	Categoria incluir(Categoria entity);

	Categoria alterar(Integer codigo, Categoria entity);
 
	Categoria consultarByCodigo(Integer codigo);
	
	List<Categoria> consultar();

	void excluir(Integer codigo);
}

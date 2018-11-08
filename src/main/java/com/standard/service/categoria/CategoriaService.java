package com.standard.service.categoria;

import java.util.List;
import java.util.Map;

import com.standard.domain.Categoria;

public interface CategoriaService {

	Categoria incluir(Categoria entity);

	Categoria alterar(Integer codigo, Categoria entity);
 
	Categoria consultarByCodigo(Integer codigo);

	Map<Long, Categoria> consultaCategoriaSubCategoria();
	
	List<Categoria> consultar();

	void excluir(Integer codigo);
}

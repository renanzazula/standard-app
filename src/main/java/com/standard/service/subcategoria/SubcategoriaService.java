package com.standard.service.subcategoria;

import com.standard.domain.Subcategory;

import java.util.List;

public interface SubcategoriaService {

	Subcategory save(Subcategory objc);

	Subcategory update(Long codigo, Subcategory objc);

	void delete(Long codigo);
	
	List<Subcategory> consultar();

	Subcategory consultarByCodigo(Long codigo);
	
}

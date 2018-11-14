package com.standard.service.subCategoria;

import com.standard.domain.SubCategoria;

import java.util.List;

public interface SubCategoriaService {

	SubCategoria incluir(SubCategoria objc);

	SubCategoria alterar(Long codigo, SubCategoria objc);

	void excluir(Long codigo);
	
	List<SubCategoria> consultar();

	SubCategoria consultarByCodigo(Long codigo);
	
}

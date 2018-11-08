package com.standard.service.subCategoria;

import java.util.List;

import com.standard.domain.SubCategoria;

public interface SubCategoriaService {

	SubCategoria incluir(SubCategoria objc);

	SubCategoria alterar(Integer codigo, SubCategoria objc);

	void excluir(Integer codigo);
	
	List<SubCategoria> consultar();

	SubCategoria consultarByCodigo(Integer codigo);
	
}

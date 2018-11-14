package com.standard.service.medida;

import com.standard.domain.Medida;
import com.standard.domain.Produto;

import java.util.List;

public interface MedidaService {

	Medida incluir(Medida objct);

	Medida alterar(Long codigo, Medida objct);

	void excluir(Long codigo);

	List<Medida> consultar();

	Medida consultarByCodigo(Long codigo);



	List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto);

}

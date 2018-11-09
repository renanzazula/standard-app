package com.standard.service.medida;

import com.standard.domain.Medida;
import com.standard.domain.Produto;

import java.util.List;

public interface MedidaService {

	Medida incluir(Medida objct);

	Medida alterar(Integer codigo, Medida objct);

	void excluir(Integer codigo);

	List<Medida> consultar();

	Medida consultarByCodigo(Integer codigo);



	List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto);

}

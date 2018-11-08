package com.standard.service.medida;

import java.util.List;

import com.standard.domain.Medida;
import com.standard.domain.Produto;

public interface MedidaService {

	Medida incluir(Medida objct);

	Medida alterar(Integer codigo, Medida objct);

	void excluir(Integer codigo);

	List<Medida> consultar();

	Medida consultarByCodigo(Integer codigo);

	List<Medida> consultarByProdutoAndValor(Produto produto);

	List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto);

}

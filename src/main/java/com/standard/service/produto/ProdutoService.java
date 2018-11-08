package com.standard.service.produto;

import java.util.List;

import com.standard.domain.Produto;

public interface ProdutoService {
	Produto incluir(Produto produto);
	Produto alterar(Integer codigo, Produto produto);
	void excluir(Integer codigo);
	Produto consultarByCodigo(Integer codigo);
	List<Produto> consultar();

	Produto consultarByBarCode(Produto produto);
	boolean validarCodigoProduto(Produto produto);
}

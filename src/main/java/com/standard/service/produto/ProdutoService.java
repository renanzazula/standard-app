package com.standard.service.produto;

import com.standard.domain.Produto;

import java.util.List;

public interface ProdutoService {
	Produto incluir(Produto produto);
	Produto alterar(Long codigo, Produto produto);
	void excluir(Long codigo);
	Produto consultarByCodigo(Long codigo);
	List<Produto> consultar();

	Produto consultarByBarCode(String barcode);
	boolean validarCodigoProduto(Produto produto);
}

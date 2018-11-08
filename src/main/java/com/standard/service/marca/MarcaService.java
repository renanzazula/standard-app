package com.standard.service.marca;

import java.util.List;

import com.standard.domain.Marca;

public interface MarcaService {

	Marca incluir(Marca objct);

	Marca alterar(Integer codigo, Marca objct);

	void excluir(Integer codigo);

	List<Marca> consultar();

	Marca consultarByCodigo(Integer codigo);
}

package com.standard.service.marca;

import com.standard.domain.Marca;

import java.util.List;

public interface MarcaService {

	Marca incluir(Marca objct);

	Marca alterar(Long codigo, Marca objct);

	void excluir(Long codigo);

	List<Marca> consultar();

	Marca consultarByCodigo(Long codigo);
}

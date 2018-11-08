package com.standard.service.dominio;

import com.standard.domain.Dominio;

import java.util.List;

public interface DominioService {

	Dominio incluir(Dominio objct);

	Dominio alterar(Integer codigo, Dominio objct);

	void excluir(Integer codigo);

	List<Dominio> consultar();

	Dominio consultarByCodigo(Integer codigo);
}

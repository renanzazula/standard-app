package com.standard.service.dominio;

import com.standard.domain.Dominio;

import java.util.List;

public interface DominioService {

	Dominio incluir(Dominio objct);

	Dominio alterar(Long codigo, Dominio objct);

	void excluir(Long codigo);

	List<Dominio> consultar();

	Dominio consultarByCodigo(Long codigo);
}

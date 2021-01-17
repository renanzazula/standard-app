package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.ClienteEntity;
import com.standard.domain.Cliente;

public class ClienteEntityToClienteFunction implements Function<ClienteEntity, Cliente> {

	@Override
	public Cliente apply(ClienteEntity input) {
		Cliente output = new Cliente();
		output.setCodigo(input.getCodigo());
		return output;
	}

}

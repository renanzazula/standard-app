package com.standard.controller;

import com.standard.domain.Fornecedor;
import com.standard.service.fornecedor.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FornecedorController.BASE_URL)
public class FornecedorController {

	public static final String BASE_URL = "/api/v1/fornecedor";
	
	private final FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping({"/all"})
	@ResponseStatus(HttpStatus.OK)
	public List<Fornecedor> consultar(){
		return fornecedorService.consultar();
	}

	@GetMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public Fornecedor consultarByCodigo(@PathVariable Long codigo){
		return fornecedorService.consultarByCodigo(codigo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fornecedor incluir(@RequestBody Fornecedor obj){
		return fornecedorService.incluir(obj);
	}

	@DeleteMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long codigo){
		fornecedorService.excluir(codigo);
		 
	}

	@PutMapping({"/{codigo}"})
	public Fornecedor alterar(@PathVariable Long codigo, @RequestBody Fornecedor obj){
		return fornecedorService.alterar(codigo, obj);
	}

}

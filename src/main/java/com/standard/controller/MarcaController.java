package com.standard.controller;

import com.standard.domain.Marca;
import com.standard.service.marca.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MarcaController.BASE_URL)
public class MarcaController {

	public static final String BASE_URL = "/api/v1/marca";
	
	private final MarcaService marcaService;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	@GetMapping({"/all"})
	@ResponseStatus(HttpStatus.OK)
	public List<Marca> consultar(){
		return marcaService.consultar();
	}

	@GetMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public Marca consultarByCodigo(@PathVariable Long codigo){
		return marcaService.consultarByCodigo(codigo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Marca incluir(@RequestBody Marca obj){
		return marcaService.incluir(obj);
	}

	@DeleteMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long codigo){
		marcaService.excluir(codigo);

	}

	@PutMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public Marca alterar(@PathVariable Long codigo, @RequestBody Marca obj){
		return marcaService.alterar(codigo, obj);
	}

}

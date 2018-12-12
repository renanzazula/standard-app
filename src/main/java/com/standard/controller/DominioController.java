package com.standard.controller;


import com.standard.domain.Dominio;
import com.standard.service.dominio.DominioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DominioController.BASE_URL)
public class DominioController {

	public static final String BASE_URL = "/api/v1/dominios";

	private final DominioService dominioService;

	public DominioController(DominioService dominioService) {
		this.dominioService = dominioService;
	}

	@GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
	public List<Dominio> consultar(){
        return dominioService.consultar();
	}

	@GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Dominio  consultarByCodigo(@PathVariable Long codigo){
	 return dominioService.consultarByCodigo(codigo);
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Dominio incluir(@RequestBody Dominio dominio){
		return dominioService.incluir(dominio);
	}

	@DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long codigo){
		dominioService.excluir(codigo);
	}

	@PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
	public Dominio alterar(@PathVariable Long codigo, @RequestBody Dominio dominio){
		return dominioService.alterar(codigo, dominio);
	}
}

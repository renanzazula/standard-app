package com.standard.controller;

import com.standard.domain.Marca;
import com.standard.service.marca.MarcaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Marca Controller", tags = "marca")
@RestController
@AllArgsConstructor
@RequestMapping(MarcaController.BASE_URL)
public class MarcaController {

	public static final String BASE_URL = "/private/v1/marca";
	
	private final MarcaService marcaService;

	@GetMapping({""})
	public ResponseEntity<List<Marca>> consultar(){
		return new ResponseEntity<>(marcaService.consultar(), HttpStatus.OK);
	}

	@GetMapping({"/{codigo}"})
	public ResponseEntity<Marca> consultarByCodigo(@PathVariable Long codigo){
		return new ResponseEntity<>(marcaService.consultarByCodigo(codigo), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Marca> incluir(@RequestBody Marca obj){
		return new ResponseEntity<>(marcaService.incluir(obj), HttpStatus.CREATED);
	}

	@DeleteMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo){
		marcaService.excluir(codigo);
	}

	@PutMapping({"/{codigo}"})
	public ResponseEntity<Marca> alterar(@PathVariable Long codigo, @RequestBody Marca obj){
		return new ResponseEntity<>(marcaService.alterar(codigo, obj), HttpStatus.OK);
	}

}

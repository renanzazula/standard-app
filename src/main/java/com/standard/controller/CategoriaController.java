package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Categoria Controller")
@RestController
@RequestMapping(CategoriaController.BASE_URL)
@AllArgsConstructor
public class CategoriaController {

    public static final String BASE_URL = "/api/v1/categoria";

    private final CategoriaService categoriaService;

    @GetMapping({""})
    @ApiOperation(value = "retorna todas categorias")
    public ResponseEntity<List<Categoria>> consultar() {
        return new ResponseEntity<>(categoriaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    @ApiOperation(value = "retorna todas categorias by codigo")
    public ResponseEntity<Categoria> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(categoriaService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> incluir(@RequestBody Categoria dominio) {
        return new ResponseEntity<>(categoriaService.incluir(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        categoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categoria> alterar(@PathVariable Long codigo, @RequestBody Categoria dominio) {
        return new ResponseEntity<>(categoriaService.alterar(codigo, dominio), HttpStatus.OK);
    }
}


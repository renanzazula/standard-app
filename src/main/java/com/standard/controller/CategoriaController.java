package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(description = "Categoria Controller")
@RestController
@RequestMapping(CategoriaController.BASE_URL)
public class CategoriaController {

    public static final String BASE_URL = "/api/v1/categoria";

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "retorna todas categorias")
    public List<Categoria> consultar(){
        return categoriaService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "retorna todas categorias by codigo")
    public Categoria consultarByCodigo(@PathVariable Long codigo){
        return categoriaService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria incluir(@RequestBody Categoria dominio){
        return categoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo){
        categoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Categoria alterar(@PathVariable Long codigo, @RequestBody Categoria dominio){
        return categoriaService.alterar(codigo, dominio);
    }
}


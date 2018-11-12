package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Categoria> consultar(){
        return categoriaService.consultar();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Categoria consultarByCodigo(@PathVariable Integer id){
        return categoriaService.consultarByCodigo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria incluir(@RequestBody Categoria dominio){
        return categoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        categoriaService.excluir(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Categoria alterar(@PathVariable Integer id, @RequestBody Categoria dominio){
        return categoriaService.alterar(id, dominio);
    }
}


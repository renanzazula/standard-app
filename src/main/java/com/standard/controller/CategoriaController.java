package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.service.categoria.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Categoria>> consultar(){
        return new ResponseEntity<>(categoriaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Categoria> consultarByCodigo(@PathVariable Integer id){
        return new ResponseEntity<>(categoriaService.consultarByCodigo(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> incluir(@RequestBody Categoria dominio){
        return new ResponseEntity<>(categoriaService.incluir(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Categoria> delete(@PathVariable Integer id){
        categoriaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Categoria> alterar(@PathVariable Integer id, @RequestBody Categoria dominio){
        return new ResponseEntity<>(categoriaService.alterar(id, dominio), HttpStatus.OK);
    }

}


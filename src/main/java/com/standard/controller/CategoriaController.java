package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.categoria.CategoryService;
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

    public static final String  BASE_URL = "/private/v1/categoria";

    private final CategoryService categoryService;

    @GetMapping({""})
    @ApiOperation(value = "retorna todas categorias")
    public ResponseEntity<List<Category>> consultar() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    @ApiOperation(value = "retorna todas categorias by codigo")
    public ResponseEntity<Category> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(categoryService.findById(codigo), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> incluir(@RequestBody Category dominio) {
        return new ResponseEntity<>(categoryService.save(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        categoryService.delete(codigo);
    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> alterar(@PathVariable Long codigo, @RequestBody Category dominio) {
        return new ResponseEntity<>(categoryService.update(codigo, dominio), HttpStatus.OK);
    }
}


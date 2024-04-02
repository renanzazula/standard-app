package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.service.categoria.CategoryService;
import com.standard.service.subcategoria.SubcategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(SubCategoriaController.BASE_URL)
public class SubCategoriaController {

    public static final String BASE_URL = "/private/v1/subcategoria";

    private final SubcategoriaService subcategoriaService;
    private final CategoryService categoryService;

    @GetMapping({""})
    public ResponseEntity<List<Subcategory>> consultar() {
        return new ResponseEntity<>(subcategoriaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Subcategory> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(subcategoriaService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subcategory> incluir(@RequestBody Subcategory dominio) {
        return new ResponseEntity<>(subcategoriaService.save(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        subcategoriaService.delete(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Subcategory> alterar(@PathVariable Long codigo, @RequestBody Subcategory dominio) {
        return new ResponseEntity<>(subcategoriaService.update(codigo, dominio), HttpStatus.OK);
    }

    @GetMapping(value = "/categoria/{codigo}")
    public ResponseEntity<List<Subcategory>> consultaSubCategoriaByCategoria(@PathVariable Long codigo) {
        Category listSubCategorias = categoryService.findById(codigo);
        return new ResponseEntity<>(listSubCategorias.getSubcategories(), HttpStatus.OK);
    }
}

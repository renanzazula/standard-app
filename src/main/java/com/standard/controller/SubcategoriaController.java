package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Subcategoria;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.subcategoria.SubcategoriaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Subcategoria Controller", tags = "subcategoria")
@RestController
@AllArgsConstructor
@RequestMapping(SubcategoriaController.BASE_URL)
public class SubcategoriaController {

    public static final String BASE_URL = "/private/v1/subcategoria";

    private final SubcategoriaService subcategoriaService;
    private final CategoriaService categoriaService;

    @GetMapping({""})
    public ResponseEntity<List<Subcategoria>> consultar() {
        return new ResponseEntity<>(subcategoriaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Subcategoria> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(subcategoriaService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subcategoria> incluir(@RequestBody Subcategoria dominio) {
        return new ResponseEntity<>(subcategoriaService.incluir(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        subcategoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Subcategoria> alterar(@PathVariable Long codigo, @RequestBody Subcategoria dominio) {
        return new ResponseEntity<>(subcategoriaService.alterar(codigo, dominio), HttpStatus.OK);
    }

    @GetMapping(value = "/categoria/{codigo}")
    public ResponseEntity<List<Subcategoria>> consultaSubCategoriaByCategoria(@PathVariable Long codigo) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(codigo);
        return new ResponseEntity<>(listSubCategorias.getSubcategorias(), HttpStatus.OK);
    }
}

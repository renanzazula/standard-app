package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Subcategoria;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.subcategoria.SubcategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(SubCategoriaController.BASE_URL)
public class SubCategoriaController {

    public static final String BASE_URL = "/api/v1/subcategoria";

    private final SubcategoriaService subcategoriaService;

    private final CategoriaService categoriaService;

    public SubCategoriaController(SubcategoriaService subcategoriaService, CategoriaService categoriaService) {
        this.subcategoriaService = subcategoriaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
    public List<Subcategoria> consultar() {
        return subcategoriaService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Subcategoria consultarByCodigo(@PathVariable Long codigo) {
        return subcategoriaService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subcategoria incluir(@RequestBody Subcategoria dominio) {
        return subcategoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo) {
        subcategoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public Subcategoria alterar(@PathVariable Long codigo, @RequestBody Subcategoria dominio) {
        return subcategoriaService.alterar(codigo, dominio);
    }

    @GetMapping( value = "/categoria/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<Subcategoria> consultaSubCategoriaByCategoria(@PathVariable Long codigo) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(codigo);
        return listSubCategorias.getSubcategorias();
    }
}

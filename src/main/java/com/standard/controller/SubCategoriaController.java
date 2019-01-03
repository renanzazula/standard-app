package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Subcategoria;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.subcategoria.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(SubCategoriaController.BASE_URL)
public class SubCategoriaController {

    public static final String BASE_URL = "/api/v1/subcategoria";

    private final SubCategoriaService subCategoriaService;

    private final CategoriaService categoriaService;

    public SubCategoriaController(SubCategoriaService subSubCategoriaService, CategoriaService categoriaService) {
        this.subCategoriaService = subSubCategoriaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
    public List<Subcategoria> consultar() {
        return subCategoriaService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Subcategoria consultarByCodigo(@PathVariable Long codigo) {
        return subCategoriaService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subcategoria incluir(@RequestBody Subcategoria dominio) {
        return subCategoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo) {
        subCategoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public Subcategoria alterar(@PathVariable Long codigo, @RequestBody Subcategoria dominio) {
        return subCategoriaService.alterar(codigo, dominio);
    }

    @GetMapping( value = "/categoria/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<Subcategoria> consultaSubCategoriaByCategoria(@PathVariable Long codigo) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(codigo);
        return listSubCategorias.getSubcategorias();
    }
}

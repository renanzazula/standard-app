package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.SubCategoria;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.subCategoria.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(SubCategoriaController.BASE_URL)
public class SubCategoriaController {

    public static final String BASE_URL = "/api/v1/subCategoria";

    private final SubCategoriaService subCategoriaService;

    private final CategoriaService categoriaService;

    public SubCategoriaController(SubCategoriaService subSubCategoriaService, CategoriaService categoriaService) {
        this.subCategoriaService = subSubCategoriaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategoria> consultar() {
        return subCategoriaService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public SubCategoria consultarByCodigo(@PathVariable Long codigo) {
        return subCategoriaService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubCategoria incluir(@RequestBody SubCategoria dominio) {
        return subCategoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo) {
        subCategoriaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public SubCategoria alterar(@PathVariable Long codigo, @RequestBody SubCategoria dominio) {
        return subCategoriaService.alterar(codigo, dominio);
    }

    //fixme:
    @GetMapping( value = "/byCategoria/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategoria> consultaSubCategoriaByCategoria(@PathVariable Long codigo) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(codigo);
        return listSubCategorias.getSubCategorias();
    }
}

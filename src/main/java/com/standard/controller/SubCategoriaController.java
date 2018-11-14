package com.standard.controller;

import com.standard.domain.SubCategoria;
import com.standard.service.subCategoria.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(SubCategoriaController.BASE_URL)
public class SubCategoriaController {

    public static final String BASE_URL = "/api/v1/subCategoria";

    private final SubCategoriaService subCategoriaService;

    public SubCategoriaController(SubCategoriaService subSubCategoriaService) {
        this.subCategoriaService = subSubCategoriaService;
    }

    @GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategoria> consultar() {
        return subCategoriaService.consultar();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public SubCategoria consultarByCodigo(@PathVariable Long id) {
        return subCategoriaService.consultarByCodigo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubCategoria incluir(@RequestBody SubCategoria dominio) {
        return subCategoriaService.incluir(dominio);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        subCategoriaService.excluir(id);
    }

    @PutMapping({"/{id}"})
    public SubCategoria alterar(@PathVariable Long id, @RequestBody SubCategoria dominio) {
        return subCategoriaService.alterar(id, dominio);
    }
}

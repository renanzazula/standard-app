package com.standard.controller;

import com.standard.domain.SubCategoria;
import com.standard.service.subCategoria.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/subCategoria")
public class SubCategoriaController {

    private final SubCategoriaService subCategoriaService;

    public SubCategoriaController(SubCategoriaService subSubCategoriaService) {
        this.subCategoriaService = subSubCategoriaService;
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<SubCategoria>> consultar() {
        return new ResponseEntity<>(subCategoriaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<SubCategoria> consultarByCodigo(@PathVariable Integer id) {
        return new ResponseEntity<>(subCategoriaService.consultarByCodigo(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubCategoria> incluir(@RequestBody SubCategoria dominio) {
        return new ResponseEntity<>(subCategoriaService.incluir(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<SubCategoria> delete(@PathVariable Integer id) {
        subCategoriaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<SubCategoria> alterar(@PathVariable Integer id, @RequestBody SubCategoria dominio) {
        return new ResponseEntity<>(subCategoriaService.alterar(id, dominio), HttpStatus.OK);
    }
}

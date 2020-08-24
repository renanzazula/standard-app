package com.standard.controller;

import com.standard.domain.Fornecedor;
import com.standard.service.fornecedor.FornecedorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(FornecedorController.BASE_URL)
public class FornecedorController {

    public static final String BASE_URL = "/private/v1/fornecedor";

    private final FornecedorService fornecedorService;

    @GetMapping({""})
    public ResponseEntity<List<Fornecedor>> consultar() {
        return new ResponseEntity<>(fornecedorService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Fornecedor> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(fornecedorService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> incluir(@RequestBody Fornecedor obj) {
        return new ResponseEntity<>(fornecedorService.incluir(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        fornecedorService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Fornecedor> alterar(@PathVariable Long codigo, @RequestBody Fornecedor obj) {
        return new ResponseEntity<>(fornecedorService.alterar(codigo, obj), HttpStatus.OK);
    }

}

package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.service.formaDePagamento.FormaDePagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(FormasDePagamentoController.BASE_URL)
public class FormasDePagamentoController {

    public static final String BASE_URL = "/api/v1/formasDePagamento";

    private final FormaDePagamentoService formasDePagamentoService;

    @GetMapping({""})
    public ResponseEntity<List<FormasDePagamento>> consultar() {
        return new ResponseEntity<>(formasDePagamentoService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<FormasDePagamento> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(formasDePagamentoService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FormasDePagamento> incluir(@RequestBody FormasDePagamento obj) {
        return new ResponseEntity<>(formasDePagamentoService.incluir(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        formasDePagamentoService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<FormasDePagamento> alterar(@PathVariable Long codigo, @RequestBody FormasDePagamento obj) {
        return new ResponseEntity<>(formasDePagamentoService.alterar(codigo, obj), HttpStatus.OK);
    }
}

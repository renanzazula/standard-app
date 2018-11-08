package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.domain.FormasDePagamento;
import com.standard.service.FormaDePagamento.FormaDePagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/formaDePagamento")
public class FormaDePagamentoController {

    private FormaDePagamentoService formasDePagamentoService;

    public FormaDePagamentoController(FormaDePagamentoService formasDePagamentoService) {
        this.formasDePagamentoService = formasDePagamentoService;
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<FormasDePagamento>> consultar(){
        return new ResponseEntity<>(formasDePagamentoService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<FormasDePagamento> consultarByCodigo(@PathVariable Integer id){
        return new ResponseEntity<>(formasDePagamentoService.consultarByCodigo(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FormasDePagamento> incluir(@RequestBody FormasDePagamento obj){
        return new ResponseEntity<>(formasDePagamentoService.incluir(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<FormasDePagamento> delete(@PathVariable Integer id){
        formasDePagamentoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<FormasDePagamento> alterar(@PathVariable Integer id, @RequestBody FormasDePagamento obj){
        return new ResponseEntity<>(formasDePagamentoService.alterar(id, obj), HttpStatus.OK);
    }
}

package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.service.FormaDePagamento.FormaDePagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FormaDePagamentoController.BASE_URL)
public class FormaDePagamentoController {

    public static final String BASE_URL = "/api/v1/formaDePagamento";

    private FormaDePagamentoService formasDePagamentoService;

    public FormaDePagamentoController(FormaDePagamentoService formasDePagamentoService) {
        this.formasDePagamentoService = formasDePagamentoService;
    }

    @GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
    public List<FormasDePagamento> consultar(){
        return formasDePagamentoService.consultar();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public FormasDePagamento consultarByCodigo(@PathVariable Long id){
        return formasDePagamentoService.consultarByCodigo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormasDePagamento incluir(@RequestBody FormasDePagamento obj){
        return formasDePagamentoService.incluir(obj);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        formasDePagamentoService.excluir(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public FormasDePagamento alterar(@PathVariable Long id, @RequestBody FormasDePagamento obj){
        return formasDePagamentoService.alterar(id, obj);
    }
}

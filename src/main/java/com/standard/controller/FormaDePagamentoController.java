package com.standard.controller;

import com.standard.domain.FormasDePagamento;
import com.standard.service.FormaDePagamento.FormaDePagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FormaDePagamentoController.BASE_URL)
public class FormaDePagamentoController {

    public static final String BASE_URL = "/api/v1/formasDepagamento";

    private final FormaDePagamentoService formasDePagamentoService;

    public FormaDePagamentoController(FormaDePagamentoService formasDePagamentoService) {
        this.formasDePagamentoService = formasDePagamentoService;
    }

    @GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
    public List<FormasDePagamento> consultar(){
        return formasDePagamentoService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public FormasDePagamento consultarByCodigo(@PathVariable Long codigo){
        return formasDePagamentoService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormasDePagamento incluir(@RequestBody FormasDePagamento obj){
        return formasDePagamentoService.incluir(obj);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo){
        formasDePagamentoService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public FormasDePagamento alterar(@PathVariable Long codigo, @RequestBody FormasDePagamento obj){
        return formasDePagamentoService.alterar(codigo, obj);
    }
}

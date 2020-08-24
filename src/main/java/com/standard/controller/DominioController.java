package com.standard.controller;


import com.standard.domain.Dominio;
import com.standard.service.dominio.DominioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DominioController.BASE_URL)
public class DominioController {

    public static final String BASE_URL = "/private/v1/dominios";

    private final DominioService dominioService;

    @GetMapping({""})
    public ResponseEntity<List<Dominio>> consultar() {
        return new ResponseEntity<>(dominioService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Dominio> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(dominioService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dominio> incluir(@RequestBody Dominio dominio) {
        return new ResponseEntity<>(dominioService.incluir(dominio), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        dominioService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Dominio> alterar(@PathVariable Long codigo, @RequestBody Dominio dominio) {
        return new ResponseEntity<>(dominioService.alterar(codigo, dominio), HttpStatus.OK);
    }
}

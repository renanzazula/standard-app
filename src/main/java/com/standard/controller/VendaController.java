package com.standard.controller;

import com.standard.domain.Venda;
import com.standard.service.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(VendaController.BASE_URL)
public class VendaController {

    public static final String BASE_URL = "/api/v1/venda";

    @Autowired
    private VendaService vendaService;

    // 1 - avancar
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Venda avancar(@RequestBody Venda venda){
        return vendaService.incluir(venda);
    }

    // 2 - get venda by codigo
    @GetMapping("/{codigo}/confirmar")
    @ResponseStatus(HttpStatus.CREATED)
    public Venda getVendaById(@PathVariable("codigo") Long codigo){
        return vendaService.consultarByCodigo(new Venda(codigo));
    }

    // 3 - confirmar
    @PostMapping("/corfirmar")
    @ResponseStatus(HttpStatus.CREATED)
    public Venda alterarStatusVendaParaEfetuada(@RequestBody Venda venda){
        return vendaService.alterarStatusVendaParaEfetuada(venda);
    }

    // 4 imprimir recibo ou enviar por email

    // 2 cancelar venda
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Venda alterarStatusVendaParaNaoRealizada(@RequestBody Venda venda){
//        return vendaService.alterarStatusVendaParaNaoRealizada(venda);
//    }

}

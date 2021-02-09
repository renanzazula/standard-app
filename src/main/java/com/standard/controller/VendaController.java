package com.standard.controller;

import com.standard.domain.Venda;
import com.standard.service.venda.VendaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Venda Controller", tags = "venda")
@RestController
@AllArgsConstructor
@RequestMapping(VendaController.BASE_URL)
public class VendaController {

    public static final String BASE_URL = "/private/v1/venda";

    private final VendaService vendaService;

    // 1 - avancar
    @PostMapping("/create")
    public ResponseEntity<Venda> avancar(@RequestBody Venda venda) {
        return new ResponseEntity<>(vendaService.incluir(venda), HttpStatus.OK);
    }

    // 2 - get venda by codigo
    @GetMapping("/{codigo}/confirmar")
    public ResponseEntity<Venda> getVendaById(@PathVariable("codigo") Long codigo) {
        return new ResponseEntity<>(vendaService.consultarByCodigo(new Venda(codigo)), HttpStatus.OK);
    }

    // 3 - confirmar
    @PostMapping("/confirmar")
    public ResponseEntity<Venda> alterarStatusVendaParaEfetuada(@RequestBody Venda venda) {
        return new ResponseEntity<>(vendaService.alterarStatusVendaParaEfetuada(venda), HttpStatus.OK);
    }

    // 4 imprimir recibo ou enviar por email

    // 2 cancelar venda
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Venda alterarStatusVendaParaNaoRealizada(@RequestBody Venda venda){
//        return vendaService.alterarStatusVendaParaNaoRealizada(venda);
//    }

}

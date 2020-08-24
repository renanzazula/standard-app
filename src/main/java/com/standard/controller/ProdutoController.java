package com.standard.controller;

import com.standard.domain.Produto;
import com.standard.service.produto.ProdutoService;
import com.standard.util.DoubleFormat;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ProdutoController.BASE_URL)
public class ProdutoController {

    public static final String BASE_URL = "/private/v1/produto";

    private final ProdutoService produtoService;

    @GetMapping({""})
    public ResponseEntity<List<Produto>> consultar() {
        return new ResponseEntity<>(produtoService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Produto> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(produtoService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> incluir(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.incluir(produto), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        produtoService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Produto> alterar(@PathVariable Long codigo, @RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.alterar(codigo, produto), HttpStatus.OK);
    }

    @GetMapping(value = "/addicionarProduto/{barCode}")
    public ResponseEntity<Produto> addicionarProduto(@PathVariable String barCode) {
        return new ResponseEntity<>(produtoService.consultarByBarCode(barCode), HttpStatus.OK);
    }

    @GetMapping("/calcular/desconto/{porcentagem}/{valor}/{precoVenda}")
    public ResponseEntity<String> calcularDesconto(@PathVariable String porcentagem, @PathVariable String valor, @PathVariable String precoVenda) {
        Double dPorcentagem = Double.parseDouble(porcentagem);
        Double dValor = Double.parseDouble(valor);
        Double dValorDesconto = (dValor*dPorcentagem)/100;
        Double dPrecoVenda = (Double.parseDouble(precoVenda) - dValorDesconto);
        return new ResponseEntity<>(String.valueOf(DoubleFormat.round(dPrecoVenda, 2)), HttpStatus.OK);
    }

    @GetMapping("/calcular/precoVenda/{porcentagem}/{precoCusto}")
    public ResponseEntity<String> calcularPrecoVenda(@PathVariable String porcentagem, @PathVariable String precoCusto) {
        Integer iPorcentagem = Integer.parseInt(porcentagem);
        Double dPrecoCusto =  Double.parseDouble(precoCusto);
        Double precoVenda = (dPrecoCusto / iPorcentagem);
        Double precoVendaFinal = (precoVenda * 100);
        return new ResponseEntity<>(String.valueOf(DoubleFormat.round(precoVendaFinal, 2)), HttpStatus.OK);
    }

    
    
    
}

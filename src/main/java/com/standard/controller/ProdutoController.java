package com.standard.controller;

import com.standard.domain.Product;
import com.standard.service.product.ProductService;
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

    private final ProductService productService;

    @GetMapping({""})
    public ResponseEntity<List<Product>> consultar() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Product> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(productService.getById(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> incluir(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        productService.delete(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Product> alterar(@PathVariable Long codigo, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(codigo, product), HttpStatus.OK);
    }

    @GetMapping(value = "/addicionarProduto/{barCode}")
    public ResponseEntity<Product> addicionarProduto(@PathVariable String barCode) {
        return new ResponseEntity<>(productService.getByBarCode(barCode), HttpStatus.OK);
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

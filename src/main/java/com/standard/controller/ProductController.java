package com.standard.controller;

import com.standard.domain.Product;
import com.standard.service.product.ProductService;
import com.standard.util.DoubleFormat;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL = "/private/v1/product";

    private final ProductService productService;

    @GetMapping({""})
    @ApiOperation(value = "find all products")
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find product by id")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create new product")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete product by id")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update product by id")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
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

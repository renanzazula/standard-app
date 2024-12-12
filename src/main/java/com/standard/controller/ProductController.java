package com.standard.controller;

import com.standard.domain.Product;
import com.standard.service.product.ProductService;
import com.standard.util.DoubleFormat;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL = "/private/api/v1/product";

    private final ProductService productService;

    @GetMapping({""})
    @PreAuthorize("hasAuthority('PRODUCT_SEARCH')")
    @ApiOperation(value = "find all products")
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find product by id")
    @PreAuthorize("hasAuthority('PRODUCT_SEARCH')")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create new product")
    @PreAuthorize("hasAuthority('PRODUCT_ADD')")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    @ApiOperation(value = "delete product by id")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @ApiOperation(value = "update product by id")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
    }

    @GetMapping(value = "/addProduct/{barCode}")
    public ResponseEntity<Product> addProduct(@PathVariable String barCode) {
        return new ResponseEntity<>(productService.getByBarCode(barCode), HttpStatus.OK);
    }

    @GetMapping("/calculate/discount/{percent}/{valor}/{totalOrder}")
    public ResponseEntity<String> calculateDiscount(@PathVariable String percent, @PathVariable String valor, @PathVariable String totalOrder) {
        Double dPercent = Double.parseDouble(percent);
        Double dAmount = Double.parseDouble(valor);
        double dDiscount = (dAmount * dPercent)/100;
        double dAmountOrder = (Double.parseDouble(totalOrder) - dDiscount);
        return new ResponseEntity<>(String.valueOf(DoubleFormat.round(dAmountOrder, 2)), HttpStatus.OK);
    }

    @GetMapping("/calculate/orderAmount/{percent}/{priceCost}")
    public ResponseEntity<String> calculateOrderAmount(@PathVariable String percent, @PathVariable String priceCost) {
        Integer iPercent = Integer.parseInt(percent);
        Double dPriceCost =  Double.parseDouble(priceCost);
        double orderAmount = (dPriceCost / iPercent);
        double finalOrderAmount = (orderAmount * 100);
        return new ResponseEntity<>(String.valueOf(DoubleFormat.round(finalOrderAmount, 2)), HttpStatus.OK);
    }

    
    
    
}

package com.standard.controller;

import com.standard.domain.ItemsTypeMeasure;
import com.standard.domain.Measure;
import com.standard.domain.Product;
import com.standard.domain.ProductHasItemsTypeMeasure;
import com.standard.service.measure.MeasureService;
import com.standard.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DomainController.BASE_URL)
public class ItemsTypeMeasureController {

    public static final String BASE_URL = "/private/v1/itensMedida";

    private final MeasureService measureService;
    private final ProductService productService;

    // fixme: separar end point...
    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    public ResponseEntity<List<Measure>> ajaxConsultarItensMedidaByCategoria(@RequestBody Product product) {
        return new ResponseEntity<>(measureService.findByCategorySubcategoryBrand(product), HttpStatus.OK);
    }

    @GetMapping(value = "/byProduct/{id}")
    public  ResponseEntity<List<ProductHasItemsTypeMeasure>> ajaxFindItemsTypeMeasureByMeasureByProductId(@PathVariable String id) {
        Product productDB = productService.getById(Long.valueOf(id));
        return new ResponseEntity<>(productDB.getProductHasItemsTypeMeasure(), HttpStatus.OK);
    }

    @GetMapping(value = "/byMeasure/{id}")
    public ResponseEntity<List<ItemsTypeMeasure>> ajaxFindItemsTypeMeasureByMeasureId(@RequestBody Measure measure, @PathVariable String id) {
        Measure measures = measureService.findById(measure.getId());
        return new ResponseEntity<>(measures.getItemsTypeMeasure(), HttpStatus.OK);
    }

}

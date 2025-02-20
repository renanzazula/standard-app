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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(DomainController.BASE_URL)
public class ItemsTypeMeasureController {

    public static final String BASE_URL = "/private/api/v1/itemsMeasure";

    private final MeasureService measureService;
    private final ProductService productService;

    @RequestMapping(value = "/ajaxSearchItemsMeasureByCategory")
    public ResponseEntity<List<Measure>> findByCategorySubcategoryBrand(@RequestBody Product product) {
        return new ResponseEntity<>(measureService.findByCategorySubcategoryBrand(product), HttpStatus.OK);
    }

    @GetMapping(value = "/byProduct/{id}")
    public ResponseEntity<List<ProductHasItemsTypeMeasure>> ajaxFindItemsTypeMeasureByMeasureByProductId(@PathVariable String id) {
        Product product = productService.getById(Long.valueOf(id));
        return new ResponseEntity<>(product.getProductHasItemsTypeMeasure(), HttpStatus.OK);
    }

    @GetMapping(value = "/byMeasure/{id}")
    public ResponseEntity<List<ItemsTypeMeasure>> ajaxFindItemsTypeMeasureByMeasureId(@RequestBody Measure measure, @PathVariable String id) {
        Measure measures = measureService.findById(measure.getId());
        return new ResponseEntity<>(measures.getItemsTypeMeasure(), HttpStatus.OK);
    }

}

package com.standard.controller;

import com.standard.domain.ItemsTypeMeasure;
import com.standard.domain.Measure;
import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.service.medida.MeasureService;
import com.standard.service.produto.ProdutoService;
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
    private final ProdutoService produtoService;

    // fixme: separar end point...
    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    public ResponseEntity<List<Measure>> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
        return new ResponseEntity<>(measureService.findByCategorySubcategoryBrand(produto), HttpStatus.OK);
    }

    @GetMapping(value = "/byProduct/{id}")
    public  ResponseEntity<List<ProdutoHasItensTipoMedida>> ajaxFindItemsTypeMeasureByMeasureByProductId(@PathVariable String id) {
        Produto productDB = produtoService.consultarByCodigo(Long.valueOf(id));
        return new ResponseEntity<>(productDB.getProdutoHasItensTipoMedida(), HttpStatus.OK);
    }

    @GetMapping(value = "/byMeasure/{id}")
    public ResponseEntity<List<ItemsTypeMeasure>> ajaxFindItemsTypeMeasureByMeasureId(@RequestBody Measure measure, @PathVariable String id) {
        Measure measures = measureService.findById(measure.getId());
        return new ResponseEntity<>(measures.getItemsTypeMeasure(), HttpStatus.OK);
    }

}

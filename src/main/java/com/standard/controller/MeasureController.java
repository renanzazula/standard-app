package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Measure;
import com.standard.service.categoria.CategoryService;
import com.standard.service.marca.BrandService;
import com.standard.service.medida.MeasureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MeasureController.BASE_URL)
public class MeasureController {

    public static final String BASE_URL = "/private/v1/measure";

    private final MeasureService measureService;

    @GetMapping({""})
    public ResponseEntity<List<Measure>> findAll() {
        return new ResponseEntity<>(measureService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Measure> findById(@PathVariable Long id) {
        return new ResponseEntity<>(measureService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Measure> save(@RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.save(measure), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        measureService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Measure> update(@PathVariable Long id, @RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.update(id, measure), HttpStatus.OK);
    }

}

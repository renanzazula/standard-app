package com.standard.controller;

import com.standard.domain.Measure;
import com.standard.service.measure.MeasureService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "find all the measures")
    public ResponseEntity<List<Measure>> findAll() {
        return new ResponseEntity<>(measureService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find measure by id")
    public ResponseEntity<Measure> findById(@PathVariable Long id) {
        return new ResponseEntity<>(measureService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create measure")
    public ResponseEntity<Measure> create(@RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.create(measure), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete measure by id")
    public void delete(@PathVariable Long id) {
        measureService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update measure by id")
    public ResponseEntity<Measure> update(@PathVariable Long id, @RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.update(id, measure), HttpStatus.OK);
    }

}

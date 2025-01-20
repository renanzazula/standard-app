package com.standard.controller;

import com.standard.domain.Measure;
import com.standard.service.measure.MeasureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MeasureController.BASE_URL)
public class MeasureController {

    public static final String BASE_URL = "/private/api/v1/measure";

    private final MeasureService measureService;

    @GetMapping({""})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_SEARCH')")
    public ResponseEntity<List<Measure>> findAll() {
        return new ResponseEntity<>(measureService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_SEARCH')")
    public ResponseEntity<Measure> findById(@PathVariable Long id) {
        return new ResponseEntity<>(measureService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_ADD')")
    public ResponseEntity<Measure> create(@RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.create(measure), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        measureService.delete(id);
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_UPDATE')")
    public ResponseEntity<Measure> update(@PathVariable Long id, @RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.update(id, measure), HttpStatus.OK);
    }

}

package com.standard.controller;

import com.standard.domain.Measure;
import com.standard.service.measure.MeasureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MeasureController implements MeasureControllerApi{

    private final MeasureService measureService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_SEARCH')")
    public ResponseEntity<List<Measure>> findAllMeasures() {
        return new ResponseEntity<>(measureService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_SEARCH')")
    public ResponseEntity<Measure> findMeasureById(@PathVariable Long id) {
        return new ResponseEntity<>(measureService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_ADD')")
    public ResponseEntity<Measure> saveMeasure(@RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.create(measure), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_DELETE')")
    public ResponseEntity<Void> deleteMeasureById(@PathVariable Long id) {
        measureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('MEASURE_UPDATE')")
    public ResponseEntity<Measure> updateMeasure(@PathVariable Long id, @RequestBody Measure measure) {
        return new ResponseEntity<>(measureService.update(id, measure), HttpStatus.OK);
    }

}

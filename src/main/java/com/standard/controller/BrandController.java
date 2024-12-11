package com.standard.controller;

import com.standard.domain.Brand;
import com.standard.service.brand.BrandService;
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
@RequestMapping(BrandController.BASE_URL)
public class BrandController {

	public static final String BASE_URL = "/private/v1/brand";
	
	private final BrandService brandService;

	@GetMapping({""})
	public ResponseEntity<List<Brand>> findAll(){
		return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<Brand> findById(@PathVariable Long id){
		return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Brand> save(@RequestBody Brand obj){
		return new ResponseEntity<>(brandService.create(obj), HttpStatus.CREATED);
	}

	@DeleteMapping({"/{id}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		brandService.delete(id);
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody Brand obj){
		return new ResponseEntity<>(brandService.update(id, obj), HttpStatus.OK);
	}

}

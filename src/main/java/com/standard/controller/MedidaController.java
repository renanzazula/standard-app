package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Medida;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/medida")
public class MedidaController {

    private CategoriaService categoriaService;
    private MarcaService marcaService;
    private MedidaService medidaService;

    public MedidaController(CategoriaService categoriaService, MarcaService marcaService, MedidaService medidaService) {
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
        this.medidaService = medidaService;
    }

    @GetMapping({"/all"})
    public  ResponseEntity<List<Medida>> consultar(){
        return new ResponseEntity<>(medidaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Medida> consultarByCodigo(@PathVariable Integer id){
        return new ResponseEntity<>(medidaService.consultarByCodigo(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medida> incluir(@RequestBody Medida medida){
        return new ResponseEntity<>(medidaService.incluir(medida), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Medida> delete(@PathVariable Integer id){
        medidaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Medida> alterar(@PathVariable Integer id, @RequestBody Medida medida){
        return new ResponseEntity<>(medidaService.alterar(id, medida), HttpStatus.OK);
    }

    private Medida carregaMedida(Medida medida) {
        medida.setMarcas(marcaService.consultar());
        medida.setCategorias(categoriaService.consultar());
        if (medida.getItensTipoMedida() != null) {
            if(medida.getItensTipoMedida().size() > 0) {
                if(medida.getItensTipoMedida().get(0) != null) {
                    if(medida.getItensTipoMedida().get(0).getCategoria() != null) {
                        Categoria categoria = medida.getItensTipoMedida().get(0).getCategoria();
                        medida.setSubCategorias(categoriaService.consultarByCodigo(categoria.getCodigo()).getSubCategorias());
                    }
                }
            }
        }
        return medida;
    }
}

package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Medida;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Medida Controller", tags = "medida")
@RestController
@AllArgsConstructor
@RequestMapping(MedidaController.BASE_URL)
public class MedidaController {

    public static final String BASE_URL = "/private/v1/medida";

    private final CategoriaService categoriaService;
    private final MarcaService marcaService;
    private final MedidaService medidaService;

    @GetMapping({""})
    public ResponseEntity<List<Medida>> consultar() {
        return new ResponseEntity<>(medidaService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{codigo}"})
    public ResponseEntity<Medida> consultarByCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(medidaService.consultarByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medida> incluir(@RequestBody Medida medida) {
        return new ResponseEntity<>(medidaService.incluir(medida), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        medidaService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    public ResponseEntity<Medida> alterar(@PathVariable Long codigo, @RequestBody Medida medida) {
        return new ResponseEntity<>(medidaService.alterar(codigo, medida), HttpStatus.OK);
    }

    private Medida carregaMedida(Medida medida) {
        medida.setMarcas(marcaService.consultar());
        medida.setCategorias(categoriaService.consultar());
        if (medida.getItensTipoMedida() != null) {
            if (medida.getItensTipoMedida().size() > 0) {
                if (medida.getItensTipoMedida().get(0) != null) {
                    if (medida.getItensTipoMedida().get(0).getCategoria() != null) {
                        Categoria categoria = medida.getItensTipoMedida().get(0).getCategoria();
                        medida.setSubcategorias(categoriaService.consultarByCodigo(categoria.getCodigo()).getSubcategorias());
                    }
                }
            }
        }
        return medida;
    }
}

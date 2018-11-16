package com.standard.controller;

import com.standard.domain.Categoria;
import com.standard.domain.Medida;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.marca.MarcaService;
import com.standard.service.medida.MedidaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MedidaController.BASE_URL)
public class MedidaController {

    public static final String BASE_URL = "/api/v1/medida";
    
    private CategoriaService categoriaService;
    private MarcaService marcaService;
    private MedidaService medidaService;

    public MedidaController(CategoriaService categoriaService, MarcaService marcaService, MedidaService medidaService) {
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
        this.medidaService = medidaService;
    }

    @GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
    public  List<Medida> consultar(){
        return medidaService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Medida consultarByCodigo(@PathVariable Long codigo){
        return medidaService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medida incluir(@RequestBody Medida medida){
        return medidaService.incluir(medida) ;
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo){
        medidaService.excluir(codigo);

    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Medida alterar(@PathVariable Long codigo, @RequestBody Medida medida){
        return medidaService.alterar(codigo, medida);
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

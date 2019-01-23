package com.standard.repository;

import com.standard.entity.CategoriaEntity;
import com.standard.entity.MarcaEntity;
import com.standard.entity.MedidaEntity;
import com.standard.entity.SubcategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, Long> {

    List<MedidaEntity> findByItensTipoMedidaCategoriaAndItensTipoMedidaSubcategoriaAndAndItensTipoMedidaMarca(
            CategoriaEntity categoria_codigo, SubcategoriaEntity subcategoria_codigo, MarcaEntity marca_codigo);

}

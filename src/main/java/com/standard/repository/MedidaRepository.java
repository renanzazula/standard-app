package com.standard.repository;

import com.standard.entity.CategoriaEntity;
import com.standard.entity.MarcaEntity;
import com.standard.entity.MedidaEntity;
import com.standard.entity.SubCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, Integer> {

    public List<MedidaEntity> findByItensTipoMedidaCategoriaAndItensTipoMedidaSubCategoriaAndAndItensTipoMedidaMarca(
            CategoriaEntity categoria_codigo, SubCategoriaEntity sub_categoria_codigo, MarcaEntity marca_codigo);

}

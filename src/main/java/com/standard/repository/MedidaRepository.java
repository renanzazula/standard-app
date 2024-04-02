package com.standard.repository;

import com.standard.entity.CategoryEntity;
import com.standard.entity.MarcaEntity;
import com.standard.entity.MedidaEntity;
import com.standard.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, Long> {

    List<MedidaEntity> findByItensTipoMedidaCategoryAndItensTipoMedidaSubcategoryAndAndItensTipoMedidaMarca(
            CategoryEntity category_id, SubcategoryEntity subcategory_id, MarcaEntity marca_id);

}

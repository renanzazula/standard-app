package com.standard.repository;

import com.standard.entity.ItemsTypeMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensTipoMedidaRepository extends JpaRepository<ItemsTypeMeasureEntity, Long> {

    ItemsTypeMeasureEntity findByMedidaCodigo(Long medidaCodigo);

}

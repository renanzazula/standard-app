package com.standard.repository;

import com.standard.entity.ItensTipoMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensTipoMedidaRepository extends JpaRepository<ItensTipoMedidaEntity, Long> {

    ItensTipoMedidaEntity findByMedidaCodigo(Integer medida_codigo);

}

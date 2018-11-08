package com.standard.repository;



import com.standard.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer>, JpaSpecificationExecutor<VendaEntity> {

    default List<VendaEntity> filter(VendaEntity venda) {
        return findAll(new VendaSpecification(venda));
    }

}

package com.standard.repository;

import com.standard.entity.FormaDePagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamentoEntity, Long> {

}

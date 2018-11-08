package com.standard.repository;

import com.standard.entity.RetiradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiradaRepository extends JpaRepository<RetiradaEntity, Integer> {

}

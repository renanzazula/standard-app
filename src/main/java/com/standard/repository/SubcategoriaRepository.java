package com.standard.repository;

import com.standard.entity.SubcategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<SubcategoriaEntity, Long> {

}

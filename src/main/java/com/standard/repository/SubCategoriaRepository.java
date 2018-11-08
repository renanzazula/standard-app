package com.standard.repository;

import com.standard.entity.SubCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoriaEntity, Integer> {

}

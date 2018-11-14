package com.standard.repository;

import com.standard.entity.DominioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DominioRepository extends JpaRepository<DominioEntity, Long>  {

}

package com.standard.repository;

import com.standard.entity.PosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosRepository extends JpaRepository<PosEntity, Long> {

    @Query(value = "SELECT id FROM pos ORDER BY id DESC LIMIT 0, 1", nativeQuery = true)
    Long generateLastPosId();

    @Query(value = "SELECT * FROM pos where id = 'A' ORDER BY id DESC LIMIT 0, 1", nativeQuery = true)
    PosEntity getLastPos();


}

package com.standard.repository;

import com.standard.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {

    @Query(value = "SELECT codigo FROM caixa ORDER BY codigo DESC LIMIT 0, 1", nativeQuery = true)
    Long gerarCodigoCaixa();

    @Query(value = "SELECT * FROM caixa where status = 'A' ORDER BY codigo DESC LIMIT 0, 1", nativeQuery = true)
    CaixaEntity buscarUltimoCaixa();


}

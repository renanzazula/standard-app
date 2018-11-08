package com.standard.repository;

import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoHasItensTipoMedidaRepository extends JpaRepository<ProdutoHasItensTipoMedidaEntity, Integer> {

    ProdutoHasItensTipoMedidaEntity findByItensTipoMedidaCodigoAndProdutoCodigo(Integer itemTipoMedidaCodigo,
                                                                                Integer produtoCodigo);
}

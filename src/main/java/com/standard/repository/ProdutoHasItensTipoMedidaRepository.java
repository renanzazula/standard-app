package com.standard.repository;

import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoHasItensTipoMedidaRepository extends JpaRepository<ProductHasItemsTypeMeasureEntity, Long> {

    ProductHasItemsTypeMeasureEntity findByItensTipoMedidaCodigoAndProdutoCodigo(Long itemTipoMedidaCodigo,
                                                                                 Long produtoCodigo);
}

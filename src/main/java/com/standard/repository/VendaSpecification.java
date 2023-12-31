package com.standard.repository;


import com.standard.entity.FormaDePagamentoEntity;
import com.standard.entity.VendaEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

class VendaSpecification implements Specification<VendaEntity> {

    private final VendaEntity venda;

    public VendaSpecification(VendaEntity venda) {
        super();
        this.venda = venda;
    }

    @Override
    public Predicate toPredicate(Root<VendaEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (venda.getCodigo() != null) {
            p.getExpressions().add(cb.equal(root.get("codigo"), venda.getCodigo()));
        }

        if (venda.getData() != null) {
            p.getExpressions().add(cb.equal(root.get("data"), venda.getData()));
        }

        if (venda.getStatus() != null) {
            if(venda.getStatus().name().equals("NONE")){
                p.getExpressions().add(cb.equal(root.get("status"), venda.getStatus()));
            }
        }
        // TODO: Cliente

        if (venda.getFormaDePagamento() != null && venda.getFormaDePagamento().getCodigo() != null) {
            Join<VendaEntity, FormaDePagamentoEntity> sq = root.join("formaDePagamento");
            p.getExpressions().add(cb.equal(sq.get("codigo"), venda.getFormaDePagamento().getCodigo()));
        }
        return p;
    }
}

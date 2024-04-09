package com.standard.repository;

import com.standard.entity.PayBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayBackRepository extends JpaRepository<PayBackEntity, Long> {

}

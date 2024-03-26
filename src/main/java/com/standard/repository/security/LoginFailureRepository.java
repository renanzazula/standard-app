package com.standard.repository.security;

import com.standard.entity.security.LoginFailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailureEntity, Long> {

    List<LoginFailureEntity> findAllByUserIdAndCreatedDateAfter(Long userId, Timestamp timestamp);
}

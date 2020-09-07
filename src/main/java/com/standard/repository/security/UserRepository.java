package com.standard.repository.security;

import com.standard.entity.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    Optional<UserEntity> findByUsername(String username);
    
}

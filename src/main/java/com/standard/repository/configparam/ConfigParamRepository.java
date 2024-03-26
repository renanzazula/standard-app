package com.standard.repository.configparam;

import com.standard.entity.ConfigParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigParamRepository extends JpaRepository<ConfigParamEntity, String> {

}

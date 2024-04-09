package com.standard.function.jpa;


import com.standard.domain.security.Authority;
import org.springframework.security.core.GrantedAuthority;

import java.util.function.Function;

public class AuthorityEntityToAuthorityAdapter implements Function<GrantedAuthority, Authority> {

    @Override
    public Authority apply(GrantedAuthority authorityEntity) {
        Authority dto = new Authority();
        dto.setRole(authorityEntity.getAuthority());
        return dto;
    }


}

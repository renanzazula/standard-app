package com.standard.function.jpa;



import com.standard.domain.security.User;
import com.standard.entity.security.UserEntity;
import com.standard.entity.security.UserPasswordEntity;
import com.standard.function.JpaFunctions;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserEntityToUserAdapter implements Function<UserEntity, User> {

    @Override
    public User apply(UserEntity userEntity) {
        userEntity.setUserPassword(new UserPasswordEntity());
        User dto = new User();
        BeanUtils.copyProperties(userEntity, dto);
        dto.setAuthorities(userEntity.getAuthorities()
                                .stream()
                                    .map(JpaFunctions.authorityEntityToAuthorityDtoAdapter)
                                        .collect(Collectors.toSet()));

        return dto;
    }


}

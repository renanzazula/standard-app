package com.standard.controller;

import com.standard.domain.security.User;
import com.standard.entity.security.UserEntity;
import com.standard.function.JpaFunctions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Authentication Controller")
@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/private/api/v1/users";

    @ApiOperation(value = "get user")
    @GetMapping({""})
    public ResponseEntity<User> getUser() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(JpaFunctions.userEntityToUserDtoAdapter.apply(userEntity), HttpStatus.OK);
    }
}

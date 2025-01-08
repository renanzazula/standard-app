package com.standard.controller;

import com.standard.domain.security.User;
import com.standard.entity.security.UserEntity;
import com.standard.function.JpaFunctions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/private/api/v1/users";

    private final UserDetailsService userDetailsService;

    @GetMapping({"/{user}"})
    public ResponseEntity<User> getUser(@PathVariable("user") String username) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userEntity.getUsername().equals(username)) {
            return new ResponseEntity<>(JpaFunctions.userEntityToUserDtoAdapter.apply(userEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

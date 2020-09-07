package com.standard.controller;

import com.standard.domain.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Authentication Controller")
@RestController
@RequestMapping(AuthenticatedController.BASE_URL)
@AllArgsConstructor
public class AuthenticatedController {

    public static final String BASE_URL = "/public/v1/users/authenticate";

    @PostMapping({""})
    @ApiOperation(value = "authenticate user")
    public ResponseEntity<User> authenticate(User user) {

        System.out.println(user.toString());
        
        User test = new User();
        test.setNumber("1");
        test.setUsername("Username");
        test.setPassword("password");
        test.setFirstName("firstName");
        test.setLastName("lastName");

        return new ResponseEntity<>(test, HttpStatus.OK);
    }
    
    
}

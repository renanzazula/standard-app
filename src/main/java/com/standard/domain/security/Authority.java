package com.standard.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {


    private Integer id;
    private String role;
    private Set<User> users;
    
}

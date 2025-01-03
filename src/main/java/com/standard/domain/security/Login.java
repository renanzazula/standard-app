package com.standard.domain.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Login implements Serializable {

    @Serial
    private static final long serialVersionUID = -4417332808096709933L;

    @NotNull
    private String username;

    @NotEmpty
    private String password;


}

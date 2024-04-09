package com.standard.domain.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Login implements Serializable {

    @NotNull
    private String userId;

    @NotEmpty
    private String password;


}

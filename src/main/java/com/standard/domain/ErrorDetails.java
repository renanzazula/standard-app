package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used for more details on errors, used for example for field error details validations.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private String target;
    private String code;
    private String message;
}


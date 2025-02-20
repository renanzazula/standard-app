package com.standard.domainOld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Used for more details on errors, used for example for field error details validations.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails implements Serializable
{

    private String target;
    private String code;
    private String message;
}


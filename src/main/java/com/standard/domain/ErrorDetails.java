package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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


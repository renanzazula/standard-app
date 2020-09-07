package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error implements Serializable {

    private static final long serialVersionUID = -7754886921722274018L;

    private String target = null;
    private String message = null;
    private String reference = null;
    private Date timestamp = null;
    private List<ErrorDetails> details = new ArrayList<>();

}

package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domain implements Serializable {


    @Serial
    private static final long serialVersionUID = -4933949406995695753L;


    private Long id;

      private String name;

    private String description;

    private boolean checked;

    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date data;

    @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
    private Date hora;

    private String status;
}

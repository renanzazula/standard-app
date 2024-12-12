package com.standard.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


public @Data class Customer implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -8568637406067043051L;
	private Long id;

 

}

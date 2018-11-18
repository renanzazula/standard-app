package com.standard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data class Error implements Serializable{
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = -7754886921722274018L;
	
	
	private Date date; 
 	private String error; 
 	private String status; 
 	private String message; 
 	private String exception; 
 	  
 
 

}

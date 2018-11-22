package com.standard.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity(name = "cliente")
public @Data class ClienteEntity extends BaseAuditEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568637406067043051L;


}

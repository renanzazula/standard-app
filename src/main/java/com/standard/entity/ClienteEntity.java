package com.standard.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "cliente")
public @Data class ClienteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568637406067043051L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

}

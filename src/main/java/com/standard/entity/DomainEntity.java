package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "domain")
public @Data class DomainEntity extends BaseAuditEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4933949406995695753L;

	@NotNull
	@Column(name = "name", length = 45)
	private String name;

	@NotNull
	@Column(name = "description", length = 45)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(mappedBy = "domains")
	private Set<ProductHasItemsTypeMeasureEntity> productHasItemsTypeMeasure;

}

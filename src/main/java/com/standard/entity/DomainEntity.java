package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "domain")
public  class DomainEntity extends BaseAuditEntity{

	@Serial
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DomainEntity that = (DomainEntity) o;
		return Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status && Objects.equals(productHasItemsTypeMeasure,
				that.productHasItemsTypeMeasure);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), name, description, status, productHasItemsTypeMeasure);
	}
}

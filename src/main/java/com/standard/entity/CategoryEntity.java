package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
public class CategoryEntity extends BaseAuditEntity {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Size(max = 45)
	@Column(name = "name", length = 45)
	private String name;

	@Size(max = 45)
	@Column(name = "description", length = 45)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "category_has_subcategory", joinColumns = {
			@JoinColumn(name = "category_id", nullable = false, updatable = false, referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "subcategory_id", nullable = false, updatable = false) })
	@OrderBy("id")
	private Set<SubcategoryEntity> subcategories;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		CategoryEntity that = (CategoryEntity) o;
		return Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status && Objects.equals(subcategories, that.subcategories);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), name, description, status, subcategories);
	}
}

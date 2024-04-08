package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "category")
public @Data class CategoryEntity extends BaseAuditEntity {

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



}

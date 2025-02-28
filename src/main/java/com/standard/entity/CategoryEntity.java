package com.standard.entity;

import com.standard.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
	@JoinTable(
			name = "category_has_subcategory",
			joinColumns = {
					@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "subcategory_id", nullable = false, referencedColumnName = "id")
			}
	)
	@OrderBy("id")
	private Set<SubcategoryEntity> subcategories;


	@Override
    public boolean equals(Object o) {
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
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, status, subcategories);
    }
}

package com.standard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseAuditEntity {

    @Column(name = "customer_name")
    private String customerName;

    @Column(length = 36, columnDefinition = "varchar")
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> orderRequests;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<UserEntity> users;

    // recebimento
    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> order;

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(customerName, that.customerName) && Objects.equals(apiKey, that.apiKey) && Objects.equals(orderRequests, that.orderRequests) && Objects.equals(order,
                that.order);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), customerName, apiKey, orderRequests, order);
    }
}

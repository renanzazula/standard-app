package com.standard.entity;

import com.standard.entity.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserEntity> users;

    // recebimento
    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> order;
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<UserEntity> users;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(customerName, that.customerName) && Objects.equals(apiKey, that.apiKey) && Objects.equals(orderRequests, that.orderRequests) && Objects.equals(users,
                that.users) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), customerName, apiKey, orderRequests, users, order);
    }
}

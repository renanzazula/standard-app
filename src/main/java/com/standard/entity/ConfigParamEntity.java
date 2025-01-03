package com.standard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "config_param")
public class ConfigParamEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Column(name = "value", nullable = false, length = 150)
    private String value;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConfigParamEntity that = (ConfigParamEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, value);
    }
}

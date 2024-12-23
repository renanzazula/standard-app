package com.standard.entity.security;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login_failure")
public class LoginFailureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private UserEntity user;

    private String username;

    @Column(name = "sourceIp")
    private String sourceIp;

    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LoginFailureEntity that = (LoginFailureEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(username, that.username) && Objects.equals(sourceIp,
                that.sourceIp) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, user, username, sourceIp, createdDate, lastModifiedDate);
    }

    @Override
    public String toString()
    {
        return "LoginFailureEntity{" + "id=" + id + ", user=" + user + ", username='" + username + '\'' + ", sourceIp='" + sourceIp + '\'' + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + '}';
    }
}

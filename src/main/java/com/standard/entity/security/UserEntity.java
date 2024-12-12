package com.standard.entity.security;

import com.standard.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails, CredentialsContainer, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserPasswordEntity userPassword;

    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<RoleEntity> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customer;

    @Builder.Default
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;

    @Builder.Default
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    @Builder.Default
    @Column(name = "credentialNonExpired")
    private boolean credentialNonExpired = true;

    @Builder.Default
    @Column(name = "enable")
    private boolean enable = true;

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        for (RoleEntity role : this.roles) {
            set.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            Set<AuthorityEntity> authorities = role.getAuthorities();
            for (AuthorityEntity authorityEntity : authorities) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authorityEntity.getPermission());
                set.add(simpleGrantedAuthority);
            }
        }
        return set;
    }

    @Override
    public String getPassword() {
        return userPassword.getPassword();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public void eraseCredentials() {
        // Spring security will use this on the context, so this method we don't need to implement.
    }

}

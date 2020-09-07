package com.standard.entity.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "authority")
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role", length = 45)
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserEntity> userEntities;
}

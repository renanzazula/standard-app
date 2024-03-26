package com.standard.entity.security;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_pass")
public class UserPasswordEntity implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "password")
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

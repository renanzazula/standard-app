package com.standard.entity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

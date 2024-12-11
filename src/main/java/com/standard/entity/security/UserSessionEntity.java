package com.standard.entity.security;

import com.standard.entity.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_session")
public @Data class UserSessionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userId", length = 64)
    private String userId;

    @Column(name = "active_sessions", length = 1024)
    private String activeSessions;

    @Column(name = "timestamp", nullable = true, updatable = true)
    @Version
    private Timestamp timestamp;

    @Column(name = "application", length = 1)
    @Enumerated(EnumType.STRING)
    private ApplicationType application;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSessionEntity other = (UserSessionEntity) obj;
        if (userId == null) {
            return other.userId == null;
        } else return userId.equals(other.userId);
    }

}

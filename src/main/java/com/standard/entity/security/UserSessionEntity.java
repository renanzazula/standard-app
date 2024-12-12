package com.standard.entity.security;

import com.standard.entity.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_session")
public class UserSessionEntity implements Serializable {

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
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserSessionEntity that = (UserSessionEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(activeSessions, that.activeSessions) && Objects.equals(timestamp,
                that.timestamp) && application == that.application;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, activeSessions, timestamp, application);
    }

    @Override
    public String toString()
    {
        return "UserSessionEntity{" + "userId='" + userId + '\'' + ", activeSessions='" + activeSessions + '\'' + ", timestamp=" + timestamp + ", application=" + application + '}';
    }
}

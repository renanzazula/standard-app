package com.standard.service.security;

import com.standard.entity.security.UserSessionEntity;
import com.standard.repository.security.UserSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {


    @Override
    @Transactional
    public void unregisterUserSession(String uid, String idSessionHashed) {

    }

    /**
     * Remove, if exists, from the session registry the request current session
     * id.
     *
     * @param  value - hashed idSession to unregister
     * @param oldValue
     *            - value of the session registry
     * @return new value to save as session registry
     */
    private String buildNewValue(String value, String oldValue) {
        if (oldValue == null) {
            oldValue = "";
        }
        String[] keys = StringUtils.commaDelimitedListToStringArray(oldValue);
        List<String> newkeys = new ArrayList<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(value)) {
                continue;
            }
            newkeys.add(keys[i]);
        }
        value = StringUtils.collectionToCommaDelimitedString(newkeys);
        return value;
    }
}

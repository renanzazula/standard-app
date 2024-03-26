package com.standard.service.security;

public interface UserSessionService {

    void unregisterUserSession(String uid, String idSessionHashed);

}

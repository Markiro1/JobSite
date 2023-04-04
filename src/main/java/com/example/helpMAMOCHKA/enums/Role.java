package com.example.helpMAMOCHKA.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_RECRUITER;


    @Override
    public String getAuthority() {
        return name();
    }
}

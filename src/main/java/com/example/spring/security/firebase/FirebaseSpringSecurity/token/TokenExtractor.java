package com.example.spring.security.firebase.FirebaseSpringSecurity.token;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface TokenExtractor {
    public String getToken(HttpServletRequest httpServletRequest);
}

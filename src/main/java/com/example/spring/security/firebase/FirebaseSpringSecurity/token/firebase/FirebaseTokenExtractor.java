package com.example.spring.security.firebase.FirebaseSpringSecurity.token.firebase;

import com.example.spring.security.firebase.FirebaseSpringSecurity.token.TokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FirebaseTokenExtractor implements TokenExtractor {
    protected static final String AUTHORIZATION_HEADER = "X-Authorization-Firebase";

    @Override
    public String getToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(AUTHORIZATION_HEADER);
    }
}

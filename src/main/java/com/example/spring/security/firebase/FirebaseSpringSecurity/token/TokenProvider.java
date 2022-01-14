package com.example.spring.security.firebase.FirebaseSpringSecurity.token;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.security.core.Authentication;

public interface TokenProvider {
    AuthenticationToken generateToken(Authentication authentication);

    AuthenticationToken validateToken(String tokenString) throws FirebaseAuthException;
}

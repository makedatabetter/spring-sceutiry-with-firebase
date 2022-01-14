package com.example.spring.security.firebase.FirebaseSpringSecurity.token.firebase;

import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.TokenProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.eclipse.collections.api.factory.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FirebaseTokenProvider implements TokenProvider {

    protected static final String IDENTITY_PROVIDER = "firebase";

    @Override
    public AuthenticationToken generateToken(Authentication authentication) {
        throw new UnsupportedOperationException("Currently it is not supported");
    }

    @Override
    public AuthenticationToken validateToken(String tokenString) throws FirebaseAuthException {

        if (tokenString == null || tokenString.isEmpty()) {
            throw new IllegalArgumentException("Firebase token is null");
        }

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(tokenString);
        Map<String, Object> claims = decodedToken.getClaims();
        String uid = decodedToken.getUid();
        List<String> roles = (List<String>) claims.getOrDefault("user_roles", Lists.mutable.of("unverified_user"));
        return AuthenticationToken.builder()
                .identityProvider(IDENTITY_PROVIDER)
                .identityId(uid)
                .name(decodedToken.getName())
                .email(decodedToken.getEmail())
                .isEmailVerified(decodedToken.isEmailVerified())
                .issuer(decodedToken.getIssuer())
                .subject(uid)
                .issueTime(null)
                .expirationTime(null)
                .authorities(roles)
                .originalClaims(claims)
                .token(tokenString)
                .build();

    }
}

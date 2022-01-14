package com.example.spring.security.firebase.FirebaseSpringSecurity.token;

import lombok.*;
import org.eclipse.collections.api.factory.Maps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationToken implements Serializable {
    private String subject;
    private String issuer;
    private String name;
    private String email;
    private String identityId;
    private boolean isEmailVerified;
    private List<String> authorities;
    private Date issueTime;
    private Date expirationTime;
    private Map<String, Object> originalClaims = Maps.mutable.empty();
    private String token;
    private String identityProvider;
}

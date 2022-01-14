package com.example.spring.security.firebase.FirebaseSpringSecurity.model;

import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {
    private static final long serialVersionUID = -2598455545632939927L;
    private String identityId;
    private String name;
    private String email;
    private String mobile;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private AuthenticationToken authenticationToken;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}

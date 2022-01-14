package com.example.spring.security.firebase.FirebaseSpringSecurity.principal;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.User;
import com.example.spring.security.firebase.FirebaseSpringSecurity.model.UserPrincipal;
import com.example.spring.security.firebase.FirebaseSpringSecurity.service.UserService;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;
import org.eclipse.collections.impl.utility.ListIterate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultPrincipalResolver implements PrincipalResolver {
    @Autowired
    UserService userService;

    @Override
    public UserPrincipal buildUserPrincipal(HttpServletRequest request, HttpServletResponse response, AuthenticationToken authenticationToken) {
        User user = userService.getUserById(authenticationToken.getIdentityId()).orElse(userService.addNewUser(authenticationToken));
        return UserPrincipal.builder()
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .accountNonExpired(user.isAccountExpired())
                .enabled(user.isEnabled())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .active(user.isActive())
                .identityId(authenticationToken.getIdentityId())
                .authorities(getAuthorities(user.getRoles()))
                .authenticationToken(authenticationToken)
                .build();
    }

    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        ListIterate.forEach(roles, s -> grantedAuthorities.add(new SimpleGrantedAuthority(s)));
        return grantedAuthorities;
    }


}

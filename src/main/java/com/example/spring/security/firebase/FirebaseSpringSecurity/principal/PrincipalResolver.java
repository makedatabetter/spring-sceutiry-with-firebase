package com.example.spring.security.firebase.FirebaseSpringSecurity.principal;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.UserPrincipal;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PrincipalResolver {

    public UserPrincipal buildUserPrincipal(HttpServletRequest request, HttpServletResponse response, AuthenticationToken authenticationToken);
}

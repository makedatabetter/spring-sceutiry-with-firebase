package com.example.spring.security.firebase.FirebaseSpringSecurity.filter;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.SecurityProperties;
import com.example.spring.security.firebase.FirebaseSpringSecurity.model.UserPrincipal;
import com.example.spring.security.firebase.FirebaseSpringSecurity.principal.PrincipalResolver;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.TokenExtractor;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    PrincipalResolver principalResolver;

    TokenProvider tokenProvider;

    TokenExtractor tokenExtractor;

    SecurityProperties restSecProps;


    public TokenAuthenticationFilter(PrincipalResolver principalResolver, TokenProvider tokenProvider, TokenExtractor tokenExtractor, SecurityProperties restSecProps) {
        this.principalResolver = principalResolver;
        this.tokenProvider = tokenProvider;
        this.tokenExtractor = tokenExtractor;
        this.restSecProps = restSecProps;
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = tokenExtractor.getToken(request);

            AuthenticationToken authenticationToken = tokenProvider.validateToken(token);
            UserPrincipal userPrincipal = principalResolver.buildUserPrincipal(request, response, authenticationToken);
            var authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (FirebaseAuthException e) {
            log.error("Invalid authentication token", e);

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(mapper.writeValueAsString(
                    ResponseEntity.internalServerError()
            ));

            return;
        }
        filterChain.doFilter(request, response);
    }

}

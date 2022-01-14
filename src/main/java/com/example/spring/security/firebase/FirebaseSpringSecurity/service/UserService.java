package com.example.spring.security.firebase.FirebaseSpringSecurity.service;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.User;
import com.example.spring.security.firebase.FirebaseSpringSecurity.repo.UserRepository;
import com.example.spring.security.firebase.FirebaseSpringSecurity.token.AuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.factory.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String uuid) {
        return userRepository.findById(uuid);
    }

    public User addNewUser(AuthenticationToken token) {
        Map<String, String> firebaseAttributes = getFirebaseAttributes(token);

        User newUser = User.builder()
                .uid(token.getIdentityId())
                .name(token.getName())
                .email(token.getEmail())
                .mobile(firebaseAttributes.get("phone_number"))
                .isEmailVerified(token.isEmailVerified())
                .issuer(token.getIssuer())
                .isAccountExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(true)
                .active(true)
                .roles(firebaseAttributes.get("user_role").isEmpty() ? List.of("ROLE_ADMIN") : token.getAuthorities())
                .build();
        return userRepository.save(newUser);

    }

    private Map<String, String> getFirebaseAttributes(AuthenticationToken authToken) {
        Map<String, String> attributes = Maps.mutable.empty();
        attributes.put("identityId", authToken.getIdentityId());

        var originalClaims = authToken.getOriginalClaims();
        attributes.put("image_url", (String) originalClaims.getOrDefault("picture", StringUtils.EMPTY));

        Map<String, Object> firebaseMap = (Map<String, Object>) originalClaims.getOrDefault("firebase", Map.of());

        attributes.put("sign_in_provider", (String) firebaseMap.getOrDefault("sign_in_provider", StringUtils.EMPTY));
        attributes.put("name", (String) originalClaims.getOrDefault("name", StringUtils.EMPTY));
        attributes.put("phone_number", (String) originalClaims.getOrDefault("phone_number", StringUtils.EMPTY));
        attributes.put("email", (String) originalClaims.getOrDefault("email", StringUtils.EMPTY));
        attributes.put("user_role", (String) originalClaims.getOrDefault("role", StringUtils.EMPTY));

        return attributes;
    }
}

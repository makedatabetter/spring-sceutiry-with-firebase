package com.example.spring.security.firebase.FirebaseSpringSecurity.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4408418647685225829L;
    @Id
    private String uid;
    private String name;
    private String email;
    private String mobile;
    private boolean isEmailVerified;
    private String issuer;
    private List<String> roles;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean active;
    private boolean isAccountExpired;
}

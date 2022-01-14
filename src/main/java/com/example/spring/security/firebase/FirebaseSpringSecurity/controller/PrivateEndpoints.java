package com.example.spring.security.firebase.FirebaseSpringSecurity.controller;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.User;
import com.example.spring.security.firebase.FirebaseSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("private")
public class PrivateEndpoints {
    @Autowired
    UserService userService;

    @GetMapping("user-details")
    @RolesAllowed({"ROLE_NORMAL","ROLE_ADMIN"})
    public ResponseEntity<List<User>> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
package com.example.spring.security.firebase.FirebaseSpringSecurity.repo;

import com.example.spring.security.firebase.FirebaseSpringSecurity.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByName(String name);
}

package com.example.spring.security.firebase.FirebaseSpringSecurity.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    @Primary
    public void firebaseInit() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(this.getClass().getClassLoader()
                        .getResourceAsStream("spring-security1-firebase-adminsdk-xrej8-fb667ff449.json")))
                .build();

        FirebaseApp.initializeApp(options);

    }

}

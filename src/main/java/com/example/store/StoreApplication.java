package com.example.store;

import com.example.store.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
        Category category = new Category();
//        var user = User.builder()
//                .name("John")
//                .password("password")
//                .email("john@gmail.com")
//                .build();
//        Profile profile = Profile.builder()
//                        .bio("bio")
//                        .build();
//        user.setProfile(profile);
//        profile.setUser(user);
//        System.out.println(user);

    }

}

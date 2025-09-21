package com.example.store.controller;

import com.example.store.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @GetMapping("/create")
    public User createUser() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@example.com");   // must set
        user.setPassword("123");             // must set
        return user;
    }

}

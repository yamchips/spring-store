package com.example.store;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private HashMap<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        if (users.containsKey(user.getEmail())) {
            throw new IllegalArgumentException("Same user exists");
        }
        users.put(user.getEmail(), user);
    }
}

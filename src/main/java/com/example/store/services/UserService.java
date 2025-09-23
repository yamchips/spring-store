package com.example.store.services;

import com.example.store.entities.User;
import com.example.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("john")
                .email("johon@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Entity is in Persistent state");
        } else {
            System.out.println("Entity is in Transient/Detached state");
        }
        userRepository.save(user);
        if (entityManager.contains(user)) {
            System.out.println("Entity is in Persistent state");
        } else {
            System.out.println("Entity is in Transient/Detached state");
        }

    }
}

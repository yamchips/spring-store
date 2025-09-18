package com.example.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {
        userRepository.save(user);
        notificationService.send("This is a notification of sending an email", user.getEmail());
    }
}

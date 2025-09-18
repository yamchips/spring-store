package com.example.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService{

    @Value("${mailServer.host}")
    private String emailHost;

    @Value("${mailServer.port}")
    private String emailPort;

    @Override
    public void send(String message) {
        System.out.println("Send an email: ");
        System.out.println(message);
    }

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Send an email to user " + recipientEmail);
        System.out.println("Host: " + emailHost + " Port: " + emailPort);
        System.out.println("Content: " + message);
    }
}

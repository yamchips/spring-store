package com.example.store;

public interface NotificationService {
    void send(String message);

    void send(String message, String recipientEmail);
}

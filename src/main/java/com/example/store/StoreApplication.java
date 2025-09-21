package com.example.store;

import com.example.store.entities.Address;
import com.example.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("John")
                .password("password")
                .email("john@gmail.com")
                .build();
        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();
        user.addAddress(address);
        System.out.println(user);
        System.out.println(address);
    }

}

package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var userService = context.getBean(UserService.class);
        userService.registerUser(new User(1L, "harry", "123456", "harry@gmail.com"));
        userService.registerUser(new User(2L, "harry2", "123456", "harry@gmail.com"));
        var orderService = context.getBean(OrderService.class);
        var orderService2 = context.getBean(OrderService.class);
//        var orderService = new OrderService(new PayPalPaymentService());
        orderService.placeOrder();
        context.close();
//        var notification = context.getBean(NotificationManager.class);
//        notification.sendNotification("This is a test for notification.");
	}

}

package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
//        var orderService = context.getBean(OrderService.class);
//        var orderService = new OrderService(new PayPalPaymentService());
//        orderService.placeOrder();
        var notification = context.getBean(NotificationManager.class);
        notification.sendNotification("This is a test for notification.");
	}

}

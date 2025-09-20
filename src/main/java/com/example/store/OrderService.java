package com.example.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//@Service
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
//        System.out.println("Order service created");
    }

    @PostConstruct
    public void init() {
//        System.out.println("OrderService post construct");
    }

    @PreDestroy
    public void cleanup() {
//        System.out.println("OrderService pre destroy");
    }


    public void placeOrder() {
        paymentService.processPayment(10);
    }
}

package dev.proleterler.paymentorderapp;

import org.springframework.boot.SpringApplication;

public class TestPaymentOrderAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaymentOrderAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

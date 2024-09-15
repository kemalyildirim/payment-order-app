package dev.proleterler.orderservice.payment;

import dev.proleterler.orderservice.PaymentOrderAppApplication;
import org.springframework.boot.SpringApplication;

public class TestPaymentOrderAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaymentOrderAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

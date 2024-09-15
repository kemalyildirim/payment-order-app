package dev.proleterler.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.proleterler.kafka", "dev.proleterler.orderservice"})
public class PaymentOrderAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentOrderAppApplication.class, args);
    }

}

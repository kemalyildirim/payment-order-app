package dev.proleterler.paymentorderapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PaymentOrderAppApplicationTests {

    @Test
    void contextLoads() {
    }

}

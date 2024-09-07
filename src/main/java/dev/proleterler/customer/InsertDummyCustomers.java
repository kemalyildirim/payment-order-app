package dev.proleterler.customer;

import dev.proleterler.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InsertDummyCustomers implements CommandLineRunner {
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        record CustomerPojo(String name, String email) {
        }
        var customerToSave = List.of(
                new CustomerPojo("kemal", "kemal@k.com"),
                new CustomerPojo("kadir", "kadir@k.com"),
                new CustomerPojo("mert", "mert@k.com"),
                new CustomerPojo("ersin", "ersin@k.com"),
                new CustomerPojo("omer", "omer@k.com")
        );
        if (customerService.getCustomerCount() == 0) {
            log.info("Inserting dummy customers of size: {}", customerToSave.size());
            for (var p : customerToSave) {
                customerService.saveCustomer(p.name, p.email, Optional.empty());
            }
        }
    }
}

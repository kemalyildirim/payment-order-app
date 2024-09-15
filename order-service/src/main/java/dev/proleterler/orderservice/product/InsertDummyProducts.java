package dev.proleterler.orderservice.product;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InsertDummyProducts implements CommandLineRunner {
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        record ProductPojo(String name, int stockQuantity, BigDecimal price) {
        }
        var productsToSave = List.of(
                new ProductPojo("phone", 100, new BigDecimal("35700.88")),
                new ProductPojo("computer", 50, new BigDecimal("60500.99")),
                new ProductPojo("tablet", 20, new BigDecimal("7500.00")),
                new ProductPojo("keyboard", 200, new BigDecimal("2545.70")),
                new ProductPojo("mouse", 250, new BigDecimal("3300.33"))
        );
        if (productService.getTotalProducts() == 0) {
            log.info("Inserting dummy products of size: {}", productsToSave.size());
            for (var p : productsToSave) {
                productService.saveProduct(p.name, p.stockQuantity, p.price);
            }
        }
    }
}

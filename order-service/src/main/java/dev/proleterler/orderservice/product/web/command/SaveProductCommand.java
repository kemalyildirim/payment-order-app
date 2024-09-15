package dev.proleterler.orderservice.product.web.command;

import java.math.BigDecimal;
import java.util.Optional;

public record SaveProductCommand(
        String name,
        Optional<String> description,
        int stockQuantity,
        BigDecimal price
) {
}

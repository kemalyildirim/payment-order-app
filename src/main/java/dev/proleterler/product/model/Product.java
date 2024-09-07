package dev.proleterler.product.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        Optional<String> description,
        BigDecimal price,
        int stockQuantity,
        LocalDateTime createdAt
) {
}

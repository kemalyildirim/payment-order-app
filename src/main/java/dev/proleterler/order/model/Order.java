package dev.proleterler.order.model;

import dev.proleterler.generated.jooq.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Order(UUID id,
                    UUID customerId,
                    OrderStatus orderStatus,
                    BigDecimal totalPrice,
                    LocalDateTime createdAt) {
}

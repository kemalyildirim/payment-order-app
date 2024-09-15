package dev.proleterler.orderservice.order.model;

import dev.proleterler.orderservice.generated.jooq.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Order(UUID id,
                    UUID customerId,
                    OrderStatus orderStatus,
                    BigDecimal totalPrice,
                    LocalDateTime createdAt) {
}

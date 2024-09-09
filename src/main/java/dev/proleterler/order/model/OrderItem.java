package dev.proleterler.order.model;

import java.util.UUID;

public record OrderItem(UUID id,
                        UUID orderId,
                        UUID productId,
                        int quantity) {
}


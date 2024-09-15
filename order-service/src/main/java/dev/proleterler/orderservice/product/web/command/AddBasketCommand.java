package dev.proleterler.orderservice.product.web.command;

import java.util.UUID;

public record AddBasketCommand(UUID customerId,
                               UUID productId,
                               int quantity) {
}

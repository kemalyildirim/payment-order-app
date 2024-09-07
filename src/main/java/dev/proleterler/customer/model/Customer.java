package dev.proleterler.customer.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record Customer(UUID id,
                       String name,
                       String email,
                       Optional<String> phoneNumber,
                       LocalDateTime createdAt) {
}

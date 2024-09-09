package dev.proleterler.order.web;

import dev.proleterler.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Void> createOrder(@PathVariable UUID customerId){
        orderService.createOrder(customerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

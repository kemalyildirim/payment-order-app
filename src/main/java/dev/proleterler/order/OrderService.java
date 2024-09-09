package dev.proleterler.order;

import dev.proleterler.order.model.Order;
import dev.proleterler.order.persistence.OrderRepository;
import dev.proleterler.product.BasketService;
import dev.proleterler.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final ProductService productService;

    public Order createOrder(UUID customerId) {
        var basketMap = basketService.getBasketMap(customerId);
        if(basketMap.isEmpty()) {
            throw new RuntimeException();
        }
        var productIds = basketMap.keySet();
        var productPriceMap = productService.getPricePerProduct(productIds);
        BigDecimal totalPrice = new BigDecimal(0);
        for (var entry : productPriceMap.entrySet()){
            var count = basketMap.get(entry.getKey());
            totalPrice = totalPrice.add((entry.getValue().multiply(new BigDecimal(count))));
        }
        var order = orderRepository.saveOrder(customerId, totalPrice);
        orderRepository.saveOrderItem(order.id(), basketMap);
        return order;
    }
}

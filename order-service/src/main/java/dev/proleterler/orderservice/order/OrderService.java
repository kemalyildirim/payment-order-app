package dev.proleterler.orderservice.order;

import dev.proleterler.kafka.model.OrderAvroModel;
import dev.proleterler.orderservice.order.model.Order;
import dev.proleterler.orderservice.order.persistence.OrderRepository;
import dev.proleterler.orderservice.product.BasketService;
import dev.proleterler.orderservice.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final ProductService productService;
    private final OrderProducerService producerService;

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
        producerService.publishOrder(
                OrderAvroModel.newBuilder()
                        .setId(order.id().toString())
                        .setOrderStatus(order.orderStatus().toString())
                        .setCustomerId(order.customerId().toString())
                        .setTotalPrice(bigDecimalToBytes(order.totalPrice()))
                        .setCreatedAt(order.createdAt())
                        .build()
        );
        return order;
    }

    private static ByteBuffer bigDecimalToBytes(BigDecimal bigDecimal) {
        // Convert BigDecimal to its unscaled value and scale
        BigInteger unscaledValue = bigDecimal.unscaledValue();
        int scale = bigDecimal.scale();

        // Convert the unscaled value to byte array
        byte[] unscaledBytes = unscaledValue.toByteArray();

        // Allocate ByteBuffer to store both the scale and the unscaled bytes
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + unscaledBytes.length); // 4 bytes for scale (int)
        byteBuffer.putInt(scale); // Store the scale
        byteBuffer.put(unscaledBytes); // Store the unscaled value

        return byteBuffer; // Return combined byte array
    }
}

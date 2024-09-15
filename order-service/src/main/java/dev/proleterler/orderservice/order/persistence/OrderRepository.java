package dev.proleterler.orderservice.order.persistence;

import dev.proleterler.orderservice.order.model.Order;
import dev.proleterler.orderservice.order.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static dev.proleterler.orderservice.generated.jooq.tables.OrderItems.ORDER_ITEMS;
import static dev.proleterler.orderservice.generated.jooq.tables.Orders.ORDERS;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final DSLContext dslContext;

    public Order saveOrder(UUID customerId, BigDecimal totalPrice){
        return dslContext.insertInto(ORDERS).columns(ORDERS.CUSTOMER_ID, ORDERS.TOTAL_PRICE).values(customerId, totalPrice).returning().fetchOneInto(Order.class);
    }

    public List<OrderItem> saveOrderItem(UUID orderId, Map<UUID, Integer> basketMap) {
        var insertValuesStep = dslContext.insertInto(ORDER_ITEMS, ORDER_ITEMS.ORDER_ID, ORDER_ITEMS.PRODUCT_ID, ORDER_ITEMS.QUANTITY);
        for (var entry : basketMap.entrySet()) {
            insertValuesStep.values(orderId, entry.getKey(), entry.getValue());
        }
        return insertValuesStep.returning().fetchInto(OrderItem.class);
    }

}

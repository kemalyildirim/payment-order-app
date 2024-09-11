package dev.proleterler.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.proleterler.order.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {
//    private final KafkaTemplate<String, Order> kafkaTemplate;
    //TODO: Configure Object serialization instead of String values.
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private static final String ORDERS_TOPIC = "orders-topic";

    public void publishOrder(Order order) {
        String orderJson;
        try {
            orderJson = mapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot serialize to JSON", e);
        }
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(ORDERS_TOPIC,
                                                                                 orderJson);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[" + order.id() +
                                           "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error("Unable to send message={} due to : {}", order.id(), ex.getMessage(), ex);
            }
        });
    }
}

package dev.proleterler.orderservice.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.proleterler.kafka.model.OrderAvroModel;
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
    private final KafkaTemplate<String, OrderAvroModel> kafkaTemplate;
    private static final String ORDERS_TOPIC = "orders-topic";

    public void publishOrder(OrderAvroModel orderAvroModel) {
        CompletableFuture<SendResult<String, OrderAvroModel>> future = kafkaTemplate.send(ORDERS_TOPIC, orderAvroModel);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[" + orderAvroModel.getId().toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error(
                        "Unable to send message={} due to : {}",
                        orderAvroModel.getId().toString(),
                        ex.getMessage(),
                        ex
                );
            }
        });
    }
}

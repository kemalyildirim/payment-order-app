package dev.proleterler.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private List<String> topicsToCreate = List.of("orders-topic", "payments-topic");

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        var kafkaAdmin = new KafkaAdmin(configs);

        var newTopics = topicsToCreate.stream().map(name -> new NewTopic(name, 1, (short) 1)).toList();
        kafkaAdmin.createOrModifyTopics(newTopics.toArray(NewTopic[]::new));
        return kafkaAdmin;
    }
}

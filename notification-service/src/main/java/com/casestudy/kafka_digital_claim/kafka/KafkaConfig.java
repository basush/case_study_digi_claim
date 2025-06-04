package com.casestudy.kafka_digital_claim.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

// Annotations
@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public DefaultKafkaConsumerFactory<String, ClaimEvent> consumerFactory() {

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "email_notify");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);

        //JsonDeserializer<ClaimEvent> jsonDeserializer = new JsonDeserializer<>();
        //jsonDeserializer.addTrustedPackages("com.casestudy.kafka_digital_claim");
        //jsonDeserializer.setUseTypeHeaders(false);
        //jsonDeserializer.setUseTypeHeader(false);
        //jsonDeserializer.setValueClass(MyJsonType.class);
        /*return new DefaultKafkaConsumerFactory<>(
                config, new StringDeserializer(),
                new JsonDeserializer<>(ClaimEvent.class));*/
        //return new DefaultKafkaConsumerFactory<>(config, null, jsonDeserializer);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener
    public ConcurrentKafkaListenerContainerFactory listenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<
                String, ClaimEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}

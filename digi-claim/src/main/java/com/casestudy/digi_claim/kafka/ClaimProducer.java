package com.casestudy.digi_claim.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClaimProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimProducer.class);
    private NewTopic topic;

    public ClaimProducer(NewTopic topic, KafkaTemplate<String, ClaimEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private KafkaTemplate<String, ClaimEvent> kafkaTemplate;

    public void sendMessage(ClaimEvent claimEvent){
        LOGGER.info(String.format("Message sent for policy number -> %s", claimEvent.getMessage()));
        Message<ClaimEvent> message = MessageBuilder.withPayload(claimEvent)
                .setHeader(KafkaHeaders.TOPIC,topic.name()).build();
        kafkaTemplate.send(message);
    }
}

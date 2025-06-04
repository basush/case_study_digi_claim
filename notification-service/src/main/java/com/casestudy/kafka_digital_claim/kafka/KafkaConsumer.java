package com.casestudy.kafka_digital_claim.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "claim_topics", groupId = "email_notify"
            //,containerFactory = "listenerContainerFactory"
    )
    public void consume(ClaimEvent claimEvent) {
        // Print statement
        System.out.println("Claim Details = " + claimEvent);


    }
}

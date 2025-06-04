package com.casestudy.kafka_digital_claim.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimEvent {
    private String message;
    private String status;
    private ClaimFormKafkaDto claimFormKafkaDto;
}

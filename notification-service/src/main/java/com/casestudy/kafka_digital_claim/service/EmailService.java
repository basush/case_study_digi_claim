package com.casestudy.kafka_digital_claim.service;

import com.casestudy.kafka_digital_claim.dto.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}

package com.zenika.training.ddd.pcscol.infrastructure.messaging;

import com.zenika.training.ddd.pcscol.domain.DomainEvent;
import com.zenika.training.ddd.pcscol.domain.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

//@Service
public class KafkaEventPublisher implements EventPublisher {

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
//        kafkaTemplate.send(event);
    }
}

package com.zenika.training.ddd.pcscol.infrastructure.messaging;

import com.zenika.training.ddd.pcscol.domain.DomainEvent;
import com.zenika.training.ddd.pcscol.domain.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SpringInMemoryEventPublisher implements EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}

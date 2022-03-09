package com.zenika.training.ddd.pcscol.domain;

public interface EventPublisher {

    void publish(DomainEvent event);
}

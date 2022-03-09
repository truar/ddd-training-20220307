package com.zenika.training.ddd.pcscol.infrastructure.persistence;

import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}

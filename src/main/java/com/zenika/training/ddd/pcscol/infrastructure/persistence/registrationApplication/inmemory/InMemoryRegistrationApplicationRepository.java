package com.zenika.training.ddd.pcscol.infrastructure.persistence.registrationApplication.inmemory;

import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRegistrationApplicationRepository implements RegistrationApplicationRepository {

    private Map<String, RegistrationApplication> applications = new HashMap<>();

    @Override
    public void save(RegistrationApplication registrationApplication) {
        applications.put(registrationApplication.id(), registrationApplication);
    }

    @Override
    public Optional<RegistrationApplication> findById(String id) {
        return Optional.ofNullable(applications.get(id));
    }
}

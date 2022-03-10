package com.zenika.training.ddd.pcscol.infrastructure.persistence.dossierinscription.inmemory;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRegistrationApplicationRepository implements RegistrationApplicationRepository {

    private Map<String, RegistrationApplication> dossiers = new HashMap<>();

    @Override
    public void save(RegistrationApplication registrationApplication) {
        dossiers.put(registrationApplication.id(), registrationApplication);
    }

    @Override
    public Optional<RegistrationApplication> findById(String id) {
        return Optional.ofNullable(dossiers.get(id));
    }
}

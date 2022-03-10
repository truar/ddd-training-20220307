package com.zenika.training.ddd.pcscol.infrastructure.persistence.registrationApplication.jpa;

import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationRepository;

import java.util.Optional;

public class JpaRegistrationApplicationRepository implements RegistrationApplicationRepository {

    private JpaRegistrationApplicationDao dao;

    @Override
    public void save(RegistrationApplication registrationApplication) {
//        dao.save(dossierInscription);
    }

    @Override
    public Optional<RegistrationApplication> findById(String id) {
//        return dao.findById(id);
        return null;
    }
}

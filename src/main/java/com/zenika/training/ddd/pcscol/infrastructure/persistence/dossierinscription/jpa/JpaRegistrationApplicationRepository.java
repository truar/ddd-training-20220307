package com.zenika.training.ddd.pcscol.infrastructure.persistence.dossierinscription.jpa;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationRepository;

import java.util.Optional;

public class JpaRegistrationApplicationRepository implements RegistrationApplicationRepository {

    private JpaDossierInscriptionDao dao;

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

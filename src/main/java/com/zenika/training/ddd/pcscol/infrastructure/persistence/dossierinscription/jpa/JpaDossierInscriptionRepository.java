package com.zenika.training.ddd.pcscol.infrastructure.persistence.dossierinscription.jpa;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscription;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionRepository;

import java.util.Optional;

public class JpaDossierInscriptionRepository implements DossierInscriptionRepository {

    private JpaDossierInscriptionDao dao;

    @Override
    public void save(DossierInscription dossierInscription) {
//        dao.save(dossierInscription);
    }

    @Override
    public Optional<DossierInscription> findById(String id) {
//        return dao.findById(id);
        return null;
    }
}

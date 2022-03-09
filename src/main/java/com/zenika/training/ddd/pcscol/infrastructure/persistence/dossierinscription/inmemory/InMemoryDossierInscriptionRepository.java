package com.zenika.training.ddd.pcscol.infrastructure.persistence.dossierinscription.inmemory;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscription;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryDossierInscriptionRepository implements DossierInscriptionRepository {

    private Map<String, DossierInscription> dossiers = new HashMap<>();

    @Override
    public void save(DossierInscription dossierInscription) {
        dossiers.put(dossierInscription.getId(), dossierInscription);
    }

    @Override
    public Optional<DossierInscription> findById(String id) {
        return Optional.ofNullable(dossiers.get(id));
    }
}

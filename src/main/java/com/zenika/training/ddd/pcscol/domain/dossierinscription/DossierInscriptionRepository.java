package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import java.util.Optional;

public interface DossierInscriptionRepository {

    void save(DossierInscription dossierInscription);

    Optional<DossierInscription> findById(String id);

}

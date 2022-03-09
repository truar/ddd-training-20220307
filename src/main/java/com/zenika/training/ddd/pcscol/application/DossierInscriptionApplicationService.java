package com.zenika.training.ddd.pcscol.application;

import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.Apprenant;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscription;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionRepository;

//import javax.transaction.Transactional;

public class DossierInscriptionApplicationService {

    private IdGenerator idGenerator;
    private DossierInscriptionRepository dossierInscriptionRepository;

    public DossierInscriptionApplicationService(IdGenerator idGenerator, DossierInscriptionRepository dossierInscriptionRepository) {
        this.idGenerator = idGenerator;
        this.dossierInscriptionRepository = dossierInscriptionRepository;
    }

//    @Transactional
    public DossierInscription getDossierInscription(String id) {
        return dossierInscriptionRepository.findById(id)
                .orElseThrow();
    }

//    @Transactional
    public String creerDossierInscription(String codeAcces, Apprenant apprenant) {
        String id = idGenerator.generateId();
        ObjectwithEvent<DossierInscription, DossierInscriptionCreated> object = DossierInscription.create(id, codeAcces, apprenant);
//        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossierInscriptionRepository.save(object.getObject());
        eventPublisher.publish(object.getEvent());
        return id;
    }
//    @Transactional
//    public String creerDossierInscription(CreerDossierInscriptionCommand command) {
////        String id = idGenerator.generateId();
////        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
////        dossierInscriptionRepository.save(dossier);
////        return id;
//        return null;

//    }

//    @Transactional
    public void completerDossierInscription(String idDossier, boolean isBoursier, String adresse) {
        DossierInscription dossier = dossierInscriptionRepository.findById(idDossier)
                .orElseThrow();
        InfosAdminCompletee infosAdminCompletee = dossier.completeInfosAdministratives(isBoursier, adresse);
        dossierInscriptionRepository.save(dossier);
        eventPublisher.publish(dossier.getEvents());
    }
}

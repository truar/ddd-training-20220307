package com.zenika.training.ddd.pcscol.application;

import com.zenika.training.ddd.pcscol.domain.EventPublisher;
import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscription;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionRepository;
import com.zenika.training.ddd.pcscol.domain.voeu.Voeu;
import com.zenika.training.ddd.pcscol.domain.voeu.VoeuAjoutee;
import com.zenika.training.ddd.pcscol.domain.voeu.VoeuRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class VoeuApplicationService {
    private IdGenerator idGenerator;
    private DossierInscriptionRepository dossierInscriptionRepository;
//    private VoeuRepository voeuRepository;
    private EventPublisher eventPublisher;

    public VoeuApplicationService(IdGenerator idGenerator, DossierInscriptionRepository dossierInscriptionRepository, EventPublisher eventPublisher) {
        this.idGenerator = idGenerator;
        this.dossierInscriptionRepository = dossierInscriptionRepository;
        this.eventPublisher = eventPublisher;
    }

    public void ajouterVoeu(String idDossier, String codeFormation) {
        DossierInscription dossierInscription = dossierInscriptionRepository.findById(idDossier).orElseThrow();
        String id = idGenerator.generateId();
        Voeu voeu = dossierInscription.ajouteUnVoeu(id, codeFormation, null);
//        voeuRepository.save(voeu);
        VoeuAjoutee voeuAjoutee = new VoeuAjoutee(voeu.getId(), voeu.getIdDossier() /*...*/);
        eventPublisher.publish(voeuAjoutee);
    }

}

package com.zenika.training.ddd.pcscol.application;

import com.zenika.training.ddd.pcscol.domain.EventPublisher;
import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationRepository;
import com.zenika.training.ddd.pcscol.domain.voeu.Choice;
import com.zenika.training.ddd.pcscol.domain.voeu.VoeuAjoutee;
import org.springframework.stereotype.Service;

@Service
public class VoeuApplicationService {
    private IdGenerator idGenerator;
    private RegistrationApplicationRepository registrationApplicationRepository;
//    private VoeuRepository voeuRepository;
    private EventPublisher eventPublisher;

    public VoeuApplicationService(IdGenerator idGenerator, RegistrationApplicationRepository registrationApplicationRepository, EventPublisher eventPublisher) {
        this.idGenerator = idGenerator;
        this.registrationApplicationRepository = registrationApplicationRepository;
        this.eventPublisher = eventPublisher;
    }

    public void ajouterVoeu(String idDossier, String codeFormation) {
        RegistrationApplication registrationApplication = registrationApplicationRepository.findById(idDossier).orElseThrow();
        String id = idGenerator.generateId();
        Choice choice = registrationApplication.addChoice(id, codeFormation, null);
//        voeuRepository.save(voeu);
        VoeuAjoutee voeuAjoutee = new VoeuAjoutee(choice.getId(), choice.getIdDossier() /*...*/);
        eventPublisher.publish(voeuAjoutee);
    }

}

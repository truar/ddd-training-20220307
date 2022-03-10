package com.zenika.training.ddd.pcscol.application;

import com.zenika.training.ddd.pcscol.domain.EventPublisher;
import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationRepository;
import com.zenika.training.ddd.pcscol.domain.choice.Choice;
import com.zenika.training.ddd.pcscol.domain.choice.ChoiceAdded;
import org.springframework.stereotype.Service;

@Service
public class ChoiceApplicationService {
    private final IdGenerator idGenerator;
    private final RegistrationApplicationRepository registrationApplicationRepository;
    private final EventPublisher eventPublisher;

    public ChoiceApplicationService(IdGenerator idGenerator, RegistrationApplicationRepository registrationApplicationRepository, EventPublisher eventPublisher) {
        this.idGenerator = idGenerator;
        this.registrationApplicationRepository = registrationApplicationRepository;
        this.eventPublisher = eventPublisher;
    }

    public void addChoiceToRegistrationApplication(String idApplication, String trainingCode, String periodCode) {
        RegistrationApplication registrationApplication = registrationApplicationRepository.findById(idApplication).orElseThrow();
        String id = idGenerator.generateId();
        Choice choice = registrationApplication.addChoice(id, trainingCode, periodCode);
        ChoiceAdded choiceAdded = new ChoiceAdded(choice.getId(), choice.getIdDossier());
        eventPublisher.publish(choiceAdded);
    }
}

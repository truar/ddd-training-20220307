package com.zenika.training.ddd.pcscol.listeners;

import com.zenika.training.ddd.pcscol.application.RegistrationApplicationService;
import com.zenika.training.ddd.pcscol.domain.choice.ChoiceAdded;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringInMemoryBusEventListener {

    private RegistrationApplicationService applicationService;

    @EventListener
    public void handle(ChoiceAdded choiceAdded) {
        System.out.println(choiceAdded);
//        service.addChoice(choiceAdded.getId(), choiceAdded.getIdDossier());
    }
}

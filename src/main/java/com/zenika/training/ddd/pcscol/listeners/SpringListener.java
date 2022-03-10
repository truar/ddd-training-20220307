package com.zenika.training.ddd.pcscol.listeners;

import com.zenika.training.ddd.pcscol.application.RegistrationApplicationService;
import com.zenika.training.ddd.pcscol.domain.choice.ChoiceAdded;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringListener {

    private RegistrationApplicationService service;

    @EventListener
    public void handle(ChoiceAdded choiceAdded) {
        System.out.println(choiceAdded);
    }
}

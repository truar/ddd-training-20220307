package com.zenika.training.ddd.pcscol.listeners;

import com.zenika.training.ddd.pcscol.application.DossierInscriptionApplicationService;
import com.zenika.training.ddd.pcscol.domain.voeu.VoeuAjoutee;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringListener {

    private DossierInscriptionApplicationService service;

    @EventListener
    public void handle(VoeuAjoutee voeuAjoutee) {
        System.out.println(voeuAjoutee);
//        service.ajouterVoeu
    }
}

package com.zenika.training.ddd.pcscol.config;

import com.zenika.training.ddd.pcscol.application.DossierInscriptionApplicationService;
import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DossierInscriptionApplicationService dossierInscriptionApplicationService(IdGenerator idGenerator, DossierInscriptionRepository repository) {
        return new DossierInscriptionApplicationService(idGenerator, repository);
    }

}

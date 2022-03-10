package com.zenika.training.ddd.pcscol.config;

import com.zenika.training.ddd.pcscol.application.RegistrationApplicationService;
import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RegistrationApplicationService dossierInscriptionApplicationService(IdGenerator idGenerator, RegistrationApplicationRepository repository) {
        return new RegistrationApplicationService(idGenerator, repository);
    }

}

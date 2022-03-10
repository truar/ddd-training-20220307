package com.zenika.training.ddd.pcscol.application;

import com.zenika.training.ddd.pcscol.domain.IdGenerator;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationRepository;

import java.time.LocalDate;

public class RegistrationApplicationService {

    private final IdGenerator idGenerator;
    private final RegistrationApplicationRepository registrationApplicationRepository;

    public RegistrationApplicationService(IdGenerator idGenerator, RegistrationApplicationRepository registrationApplicationRepository) {
        this.idGenerator = idGenerator;
        this.registrationApplicationRepository = registrationApplicationRepository;
    }

    public RegistrationApplication getRegistrationApplication(String id) {
        return registrationApplicationRepository.findById(id)
                .orElseThrow();
    }

    public String createRegistrationApplication(String lastName, String firstName, LocalDate birthDate) {
        String id = idGenerator.generateId();
        RegistrationApplication application = new RegistrationApplication(id, lastName, firstName, birthDate);
        registrationApplicationRepository.save(application);
        return id;
    }

    public void completeAdministrativeInformation(String idApplication, boolean scholarship, String address) {
        RegistrationApplication application = getRegistrationApplication(idApplication);
        application.completeAdministrativeInformation(scholarship, address);
        registrationApplicationRepository.save(application);
    }
}

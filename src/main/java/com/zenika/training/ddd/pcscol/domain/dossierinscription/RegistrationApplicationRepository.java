package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import java.util.Optional;

public interface RegistrationApplicationRepository {

    void save(RegistrationApplication registrationApplication);

    Optional<RegistrationApplication> findById(String id);

}

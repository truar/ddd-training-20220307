package com.zenika.training.ddd.pcscol.domain.registrationApplication;

import java.time.LocalDate;

public record Applicant(String lastName, String firstName, LocalDate birthDate) {

}

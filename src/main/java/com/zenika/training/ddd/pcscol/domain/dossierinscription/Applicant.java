package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import java.time.LocalDate;

public record Applicant(String lastName, String firstName, LocalDate birthDate) {

}

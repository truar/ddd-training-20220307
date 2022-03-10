package com.zenika.training.ddd.pcscol.domain.registrationApplication;

import com.zenika.training.ddd.pcscol.domain.choice.Choice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.zenika.training.ddd.pcscol.domain.registrationApplication.AttachmentType.IDENTITY_CARD;
import static com.zenika.training.ddd.pcscol.domain.registrationApplication.AttachmentType.PHOTO;
import static com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationStatus.*;
import static java.util.stream.Collectors.toList;

public class RegistrationApplication {

    private final String id;
    private RegistrationApplicationStatus status;
    private final Applicant applicant;
    private List<Attachment> attachments;
    private Boolean scholarship;
    private String address;
    private LocalDateTime validationDate;

    public RegistrationApplication(String id, String lastName, String firstName, LocalDate birthDate) {
        this.id = id;
        this.applicant = new Applicant(lastName, firstName, birthDate);
        status = IN_PROGRESS;
        attachments = new ArrayList<>();
    }

    public void completeAdministrativeInformation(boolean scolarship, String address) {
        this.scholarship = scolarship;
        this.address = address;
        this.status = WAITING_FOR_ATTACHMENTS;
    }

    public void attach(List<Attachment> attachments) {
        this.attachments = attachments;
        if (attachmentsContain(PHOTO) && attachmentsContain(IDENTITY_CARD)) {
            this.status = ATTACHMENT_PENDING_VALIDATION;
        }
    }

    private boolean attachmentsContain(AttachmentType type) {
        return attachments.stream().anyMatch(attachment -> attachment.getType().equals(type));
    }

    public void validate(LocalDateTime validationDate) {
        this.status = VALIDATED;
        this.validationDate = validationDate;
    }

    public Choice addChoice(String idVoeu, String codeFormation, String codePeriode) {
        // If dossier is validated
        // throw exception -> can not add voeu to a validated dossier
        return new Choice(idVoeu, id, codeFormation, codePeriode);
    }

    public String id() {
        return id;
    }

    public List<Attachment> identityCard() {
        return attachments.stream()
                .filter(attachment -> attachment.getType() == IDENTITY_CARD)
                .collect(toList());
    }

    public List<Attachment> photos() {
        return attachments.stream()
                .filter(attachment -> attachment.getType() == PHOTO)
                .collect(toList());
    }

    public RegistrationApplicationStatus status() {
        return this.status;
    }

    public String applicantLastName() {
        return applicant.lastName();
    }

    public String applicantFirstName() {
        return applicant.firstName();
    }

    public LocalDate applicantBirthDate() {
        return applicant.birthDate();
    }

    public Boolean isScholarshipApplicant() {
        return scholarship;
    }

    public String address() {
        return address;
    }

    public LocalDateTime validationDate() {
        return validationDate;
    }
}

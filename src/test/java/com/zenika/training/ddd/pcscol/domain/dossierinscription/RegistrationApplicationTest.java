package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import com.zenika.training.ddd.pcscol.domain.voeu.Choice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class RegistrationApplicationTest {

    @Test
    void creates_a_registration_application() {
        String id = "id";
        RegistrationApplication application = new RegistrationApplication(id, "lastName", "firstName", LocalDate.of(1989, 12, 7));

        assertThat(application.id()).isEqualTo(id);
        assertThat(application.applicantLastName()).isEqualTo("lastName");
        assertThat(application.applicantFirstName()).isEqualTo("firstName");
        assertThat(application.applicantBirthDate()).isEqualTo(LocalDate.of(1989, 12, 7));
        assertThat(application.status()).isEqualTo(IN_PROGRESS);
    }

    @Test
    void applicant_provides_administrative_information_to_registration_application() {
        RegistrationApplication application = anApplication();

        application.completeAdministrativeInformation(true, "1 chemin de la bosse 23456 CITY");

        assertThat(application.isScholarshipApplicant()).isTrue();
        assertThat(application.address()).isEqualTo("1 chemin de la bosse 23456 CITY");
        assertThat(application.status()).isEqualTo(WAITING_FOR_ATTACHMENTS);
    }

    @Test
    void applicant_can_attach_files_to_registration_application() {
        RegistrationApplication application = aScholarshipApplication();

        Attachment identityCard = new Attachment("http://bucket/lastName-firstName/identityCard", AttachmentType.IDENTITY_CARD);
        Attachment photo = new Attachment("http://bucket/lastName-firstName/photo", AttachmentType.PHOTO);
        List<Attachment> attachments = List.of(identityCard, photo);

        application.attach(attachments);

        assertThat(application.identityCard()).containsExactly(identityCard);
        assertThat(application.photos()).containsExactly(photo);
        assertThat(application.status()).isEqualTo(ATTACHMENT_PENDING_VALIDATION);
    }

    @Test
    void applicant_can_attach_many_files_of_same_type() {
        Attachment identityCardFront = new Attachment("http://bucket/lastName-firstName/identityCard", AttachmentType.IDENTITY_CARD);
        Attachment identityCardBack = new Attachment("http://bucket/lastName-firstName/identityCard", AttachmentType.IDENTITY_CARD);
        List<Attachment> attachments = List.of(identityCardFront, identityCardBack);
        RegistrationApplication application = aScholarshipApplication();

        application.attach(attachments);

        assertThat(application.identityCard()).containsExactlyInAnyOrder(identityCardFront, identityCardBack);
    }

    @Test
    void application_is_not_ATTACHMENT_PENDING_VALIDATION_if_there_is_no_PHOTO_attached() {
        RegistrationApplication application = aScholarshipApplication();

        Attachment identityCard = new Attachment("http://bucket/lastName-firstName/identityCard", AttachmentType.IDENTITY_CARD);
        List<Attachment> attachments = List.of(identityCard);

        application.attach(attachments);

        assertThat(application.status()).isEqualTo(WAITING_FOR_ATTACHMENTS);
    }

    @Test
    void application_is_not_ATTACHMENT_PENDING_VALIDATION_if_there_is_no_IDENTITY_CARD_attached() {
        RegistrationApplication application = aScholarshipApplication();

        Attachment photo = new Attachment("http://bucket/lastName-firstName/photo", AttachmentType.PHOTO);
        List<Attachment> attachments = List.of(photo);

        application.attach(attachments);

        assertThat(application.status()).isEqualTo(WAITING_FOR_ATTACHMENTS);
    }

    @Test
    void applicant_can_add_choice_to_registration_application() {
        RegistrationApplication application = aScholarshipApplication();
        String idChoice = "idChoice";
        String trainingCode = "LIC-CHIMIE";
        String periodCode = "PER-2021";

        Choice choice = application.addChoice(idChoice, trainingCode, periodCode);

        assertThat(choice.getId()).isEqualTo(idChoice);
        assertThat(choice.getIdDossier()).isEqualTo(application.id());
    }

    @Test
    void registration_application_can_be_validated() {
        RegistrationApplication application = aCompleteApplication();

        LocalDateTime validationDate = LocalDate.of(2022, 3, 23).atStartOfDay();
        application.validate(validationDate);

        assertThat(application.status()).isEqualTo(VALIDATED);
        assertThat(application.validationDate()).isEqualTo(validationDate);
    }

    private RegistrationApplication aCompleteApplication() {
        RegistrationApplication application = aScholarshipApplication();
        Attachment identityCard = new Attachment("http://bucket/lastName-firstName/identityCard", AttachmentType.IDENTITY_CARD);
        Attachment photo = new Attachment("http://bucket/lastName-firstName/photo", AttachmentType.PHOTO);
        List<Attachment> attachments = List.of(identityCard, photo);
        application.attach(attachments);
        return application;
    }

    private RegistrationApplication anApplication() {
        return new RegistrationApplication("id", "lastName", "firstName", LocalDate.of(1989, 12, 7));
    }

    private RegistrationApplication aScholarshipApplication() {
        RegistrationApplication application = new RegistrationApplication("id", "lastName", "firstName", LocalDate.of(1989, 12, 7));
        application.completeAdministrativeInformation(true, "1 chemin de la bosse 23456 CITY");
        return application;
    }

}

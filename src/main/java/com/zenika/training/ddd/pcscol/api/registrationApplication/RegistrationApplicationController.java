package com.zenika.training.ddd.pcscol.api.registrationApplication;

import com.zenika.training.ddd.pcscol.application.RegistrationApplicationService;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplication;
import com.zenika.training.ddd.pcscol.domain.registrationApplication.Attachment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zenika.training.ddd.pcscol.api.registrationApplication.RegistrationApplicationResponse.AttachmentResponse;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/registrationApplications")
public class RegistrationApplicationController {

    private final RegistrationApplicationService applicationService;

    public RegistrationApplicationController(RegistrationApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRegistrationApplication(@RequestBody CreateRegistrationApplicationRequest request) {
        return applicationService.createRegistrationApplication(
                request.lastName, request.firstName, request.birthDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void completeAdministrativeInformation(@RequestBody CompleteAdministrativeInformationRequest request) {
        applicationService.completeAdministrativeInformation(request.idApplication, request.scholarship, request.address);
    }

    @GetMapping("/{id}")
    public RegistrationApplicationResponse fetchRegistrationApplication(@PathVariable String id) {
        return toResponse(applicationService.getRegistrationApplication(id));
    }

    private RegistrationApplicationResponse toResponse(RegistrationApplication registrationApplication) {
        return new RegistrationApplicationResponse(registrationApplication.id(),
                registrationApplication.applicantLastName(),
                registrationApplication.applicantFirstName(),
                registrationApplication.status(),
                toAttachmentsResponse(registrationApplication.identityCard()),
                toAttachmentsResponse(registrationApplication.photos())
        );
    }

    private List<AttachmentResponse> toAttachmentsResponse(List<Attachment> attachments) {
        return attachments.stream()
                .map(pj -> new AttachmentResponse(pj.getUrl()))
                .collect(toList());
    }

}

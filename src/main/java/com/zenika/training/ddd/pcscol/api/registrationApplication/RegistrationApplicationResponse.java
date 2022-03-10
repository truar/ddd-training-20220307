package com.zenika.training.ddd.pcscol.api.registrationApplication;

import com.zenika.training.ddd.pcscol.domain.registrationApplication.RegistrationApplicationStatus;

import java.util.List;

public class RegistrationApplicationResponse {

    public String id;
    public String lastName;
    public String firstName;
    public RegistrationApplicationStatus status;
    public List<AttachmentResponse> identityCard;
    public List<AttachmentResponse> photos;

    public RegistrationApplicationResponse(String id, String lastName, String firstName, RegistrationApplicationStatus status, List<AttachmentResponse> identityCard, List<AttachmentResponse> photos) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
        this.identityCard = identityCard;
        this.photos = photos;
    }

    public static class AttachmentResponse {

        public AttachmentResponse(String url) {
            this.url = url;
        }

        public String url;
    }
}

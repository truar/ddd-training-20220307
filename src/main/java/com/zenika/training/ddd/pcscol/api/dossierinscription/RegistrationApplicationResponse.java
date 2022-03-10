package com.zenika.training.ddd.pcscol.api.dossierinscription;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.RegistrationApplicationStatus;

import java.util.List;

public class RegistrationApplicationResponse {

    public String id;
    public String nom;
    public String prenom;
    public RegistrationApplicationStatus status;
    public List<AttachmentResponse> carteIdentites;
    public List<AttachmentResponse> photos;

    public RegistrationApplicationResponse(String id, String nom, String prenom, RegistrationApplicationStatus status, List<AttachmentResponse> carteIdentites, List<AttachmentResponse> photos) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.carteIdentites = carteIdentites;
        this.photos = photos;
    }

    public static class AttachmentResponse {

        public AttachmentResponse(String url) {
            this.url = url;
        }

        public String url;
    }
}

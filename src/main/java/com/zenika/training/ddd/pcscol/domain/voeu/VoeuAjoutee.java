package com.zenika.training.ddd.pcscol.domain.voeu;

import com.zenika.training.ddd.pcscol.domain.DomainEvent;

public class VoeuAjoutee implements DomainEvent {
    private final String id;
    private final String idDossier;

    public VoeuAjoutee(String id, String idDossier) {
        this.id = id;
        this.idDossier = idDossier;
    }

    public String getId() {
        return id;
    }

    public String getIdDossier() {
        return idDossier;
    }

    @Override
    public String toString() {
        return "VoeuAjoutee{" +
                "id='" + id + '\'' +
                ", idDossier='" + idDossier + '\'' +
                '}';
    }
}

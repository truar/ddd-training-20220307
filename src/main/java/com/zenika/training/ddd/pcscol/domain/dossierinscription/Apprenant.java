package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import java.time.LocalDate;

public class Apprenant {

    private final String nom;
    private final String prenom;
    private final LocalDate dateDeNaissance;
    private Boolean isBoursier;
    private String adresse;

    public Apprenant(String nom, String prenom, LocalDate dateDeNaissance) {
        this(nom, prenom, dateDeNaissance, null, null);
    }

    public Apprenant(String nom, String prenom, LocalDate dateDeNaissance, Boolean isBoursier, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.isBoursier = isBoursier;
        this.adresse = adresse;
    }

    public void completeInfos(boolean isBoursier, String adresse) {
        this.isBoursier = isBoursier;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public boolean isBoursier() {
        return isBoursier;
    }

    public String getAdresse() {
        return adresse;
    }
}

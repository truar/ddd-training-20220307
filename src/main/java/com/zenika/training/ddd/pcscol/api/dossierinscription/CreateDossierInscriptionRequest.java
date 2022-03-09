package com.zenika.training.ddd.pcscol.api.dossierinscription;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class CreateDossierInscriptionRequest {

    public String codeAcces;
    public String nomApprenant;
    public String prenomApprenant;
    public LocalDate dateDeNaissanceApprenant;

}

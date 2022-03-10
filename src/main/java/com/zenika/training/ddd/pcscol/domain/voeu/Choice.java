package com.zenika.training.ddd.pcscol.domain.voeu;

public class Choice {
    private final String id;
    private final String idDossier;
    private final String codeFormation;
    private final String codePeriode;

    public Choice(String id, String idDossier, String codeFormation, String codePeriode) {
        this.id = id;
        this.idDossier = idDossier;
        this.codeFormation = codeFormation;
        this.codePeriode = codePeriode;
    }

    public String getId() {
        return id;
    }

    public String getIdDossier() {
        return idDossier;
    }
}

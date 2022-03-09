package com.zenika.training.ddd.pcscol.api.dossierinscription;

import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionStatus;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.PieceJointeType;

import java.util.List;

public class DossierInscriptionResponse {

    public String id;
    public String nom;
    public String prenom;
    public DossierInscriptionStatus status;
    public List<PieceJointeResponse> carteIdentites;
    public List<PieceJointeResponse> photos;

    public DossierInscriptionResponse(String id, String nom, String prenom, DossierInscriptionStatus status, List<PieceJointeResponse> carteIdentites, List<PieceJointeResponse> photos) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.carteIdentites = carteIdentites;
        this.photos = photos;
    }

    public static class PieceJointeResponse {

        public PieceJointeResponse(String url) {
            this.url = url;
        }

        public String url;
    }
}

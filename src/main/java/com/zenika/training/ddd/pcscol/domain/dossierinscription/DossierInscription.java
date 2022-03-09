package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import com.zenika.training.ddd.pcscol.domain.DomainEvent;
import com.zenika.training.ddd.pcscol.domain.voeu.Voeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionStatus.*;
import static com.zenika.training.ddd.pcscol.domain.dossierinscription.PieceJointeType.CARTE_IDENTITE;
import static com.zenika.training.ddd.pcscol.domain.dossierinscription.PieceJointeType.PHOTO;
import static java.util.stream.Collectors.groupingBy;

public class DossierInscription {

    private final String id;
    private final String codeAcces;
    private DossierInscriptionStatus status;
    private final Apprenant apprenant;
    private Map<PieceJointeType, List<PieceJointe>> piecesJointes;

    private List<DomainEvent> events;

    public DossierInscription(String id, String codeAcces, Apprenant apprenant) {
        this.id = id;
        this.codeAcces = codeAcces;
        this.apprenant = apprenant;
        status = EN_COURS;
        piecesJointes = new HashMap<>();
    }

    public boolean authenticate(String codeAcces) {
        return this.codeAcces.equals(codeAcces);
    }

    public InfosAdminCompletee completeInfosAdministratives(boolean isBoursier, String adresse) {
        this.apprenant.completeInfos(isBoursier, adresse);
        this.status = EN_ATTENTE_DE_DEPOT_DE_PJ;
        return infosAdminComplete;
    }

    public void deposer(List<PieceJointe> piecesJointes) {
        // assert contains 1 photo
        // check status if piece jointe can be uploaded
        this.piecesJointes = piecesJointes.stream()
                .collect(groupingBy(PieceJointe::getType));
        if (this.piecesJointes.containsKey(PHOTO) && this.piecesJointes.containsKey(CARTE_IDENTITE)) {
            this.status = EN_ATTENTE_DE_VALIDATION_DE_PJ;
        }
        events.add(pieceJointeDeposees);
    }

    public Voeu ajouteUnVoeu(String idVoeu, String codeFormation, String codePeriode) {
        // If dossier is validated
        // throw exception -> can not add voeu to a validated dossier
        return new Voeu(idVoeu, id, codeFormation, codePeriode);
    }

    public String getId() {
        return id;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    // Make this immutable
    public List<PieceJointe> getCarteIdentites() {
        List<PieceJointe> pieceJointes = this.piecesJointes.get(CARTE_IDENTITE);
        if (pieceJointes == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(pieceJointes);
    }

    // Make this immutable
    public List<PieceJointe> getPhotos() {
        List<PieceJointe> pieceJointes = this.piecesJointes.get(PHOTO);
        if (pieceJointes == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(pieceJointes);
    }

    public DossierInscriptionStatus getStatus() {
        return this.status;
    }
}

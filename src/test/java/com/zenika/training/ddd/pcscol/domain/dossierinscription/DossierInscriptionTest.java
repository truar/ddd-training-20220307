package com.zenika.training.ddd.pcscol.domain.dossierinscription;

import com.zenika.training.ddd.pcscol.domain.voeu.Voeu;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscriptionStatus.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class DossierInscriptionTest {

    @Test
    void cree_un_dossier_dinscription_et_autorise_lacces() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));

        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);

        assertThat(dossier.getId()).isEqualTo(id);
        assertThat(dossier.authenticate(codeAcces)).isTrue();
        assertThat(dossier.getApprenant().getNom()).isEqualTo("nom");
        assertThat(dossier.getApprenant().getPrenom()).isEqualTo("prenom");
        assertThat(dossier.getApprenant().getDateDeNaissance()).isEqualTo(LocalDate.of(1989, 12, 7));
        assertThat(dossier.getStatus()).isEqualTo(EN_COURS);
    }

    @Test
    void apprenant_peut_remplir_ses_infos_administratives() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);

        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        assertThat(dossier.getApprenant().isBoursier()).isTrue();
        assertThat(dossier.getApprenant().getAdresse()).isEqualTo("1 chemin de la bosse 23456 CITY");
        assertThat(dossier.getStatus()).isEqualTo(EN_ATTENTE_DE_DEPOT_DE_PJ);
    }

    @Test
    void apprenant_peut_deposer_les_pieces_jointes_relatives_au_dossier_dinscription() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        PieceJointe carteIdentite = new PieceJointe("http://carte_identite", PieceJointeType.CARTE_IDENTITE);
        PieceJointe photo = new PieceJointe("http://photo", PieceJointeType.PHOTO);
        List<PieceJointe> piecesJointeTeleversees = List.of(
                carteIdentite,
                photo
        );
        dossier.deposer(piecesJointeTeleversees);

        assertThat(dossier.getCarteIdentites()).containsExactly(carteIdentite);
        assertThat(dossier.getPhotos()).containsExactly(photo);
        assertThat(dossier.getStatus()).isEqualTo(EN_ATTENTE_DE_VALIDATION_DE_PJ);
    }

    @Test
    void apprenant_peut_deposer_plusieurs_pieces_jointes_de_meme_type() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        PieceJointe carteIdentiteVerso = new PieceJointe("http://carte_identite", PieceJointeType.CARTE_IDENTITE);
        PieceJointe carteIdentiteRecto = new PieceJointe("http://carte_identite", PieceJointeType.CARTE_IDENTITE);
        List<PieceJointe> piecesJointeTeleversees = List.of(
                carteIdentiteVerso,
                carteIdentiteRecto
        );
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        dossier.deposer(piecesJointeTeleversees);

        assertThat(dossier.getCarteIdentites()).containsExactlyInAnyOrder(carteIdentiteVerso, carteIdentiteRecto);
    }

    @Test
    void dossier_ne_passe_pas_a_letat_EN_ATTENTE_DE_VALIDATION_si_lapprenant_na_pas_deposer_quune_carte_sans_photo() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        PieceJointe carteIdentite = new PieceJointe("http://carte_identite", PieceJointeType.CARTE_IDENTITE);
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        dossier.deposer(singletonList(carteIdentite));

        assertThat(dossier.getStatus()).isEqualTo(EN_ATTENTE_DE_DEPOT_DE_PJ);
    }

    @Test
    void dossier_ne_passe_pas_a_letat_EN_ATTENTE_DE_VALIDATION_si_lapprenant_na_pas_deposer_quune_photo_sans_carte() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        PieceJointe photo = new PieceJointe("http://photo", PieceJointeType.PHOTO);
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        dossier.deposer(singletonList(photo));

        assertThat(dossier.getStatus()).isEqualTo(EN_ATTENTE_DE_DEPOT_DE_PJ);
    }

    @Test
    void apprenant_peut_ajouter_un_voeu_au_dossier_dinscription() {
        String id = "id";
        String codeAcces = "012345";
        Apprenant apprenant = new Apprenant("nom", "prenom", LocalDate.of(1989, 12, 7));
        PieceJointe photo = new PieceJointe("http://photo", PieceJointeType.PHOTO);
        DossierInscription dossier = new DossierInscription(id, codeAcces, apprenant);
        dossier.completeInfosAdministratives(true, "1 chemin de la bosse 23456 CITY");

        String idVoeu = "idVoeu";
        String codeFormation = "LIC-CHIMIE";
        String codePeriode = "PER-2021";
        Voeu voeu = dossier.ajouteUnVoeu(idVoeu, codeFormation, codePeriode);

        assertThat(voeu.getId()).isEqualTo(idVoeu);
        assertThat(voeu.getIdDossier()).isEqualTo(id);
    }

}

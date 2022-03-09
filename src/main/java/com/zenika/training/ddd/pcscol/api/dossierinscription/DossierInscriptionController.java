package com.zenika.training.ddd.pcscol.api.dossierinscription;

import com.zenika.training.ddd.pcscol.application.DossierInscriptionApplicationService;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.Apprenant;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.DossierInscription;
import com.zenika.training.ddd.pcscol.domain.dossierinscription.PieceJointe;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zenika.training.ddd.pcscol.api.dossierinscription.DossierInscriptionResponse.PieceJointeResponse;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/dossierInscriptions")
public class DossierInscriptionController {

    private DossierInscriptionApplicationService service;

    public DossierInscriptionController(DossierInscriptionApplicationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createDossierInscription(@RequestBody CreateDossierInscriptionRequest request) {
        return service.creerDossierInscription(request.codeAcces,
                new Apprenant(request.nomApprenant, request.prenomApprenant, request.dateDeNaissanceApprenant));
    }

    @GetMapping("/{id}")
    public DossierInscriptionResponse fetchDossierInscription(@PathVariable String id) {
        return toResponse(service.getDossierInscription(id));
    }

    private DossierInscriptionResponse toResponse(DossierInscription dossierInscription) {
        return new DossierInscriptionResponse(dossierInscription.getId(),
                dossierInscription.getApprenant().getNom(),
                dossierInscription.getApprenant().getPrenom(),
                dossierInscription.getStatus(),
                toPieceJointesResponse(dossierInscription.getCarteIdentites()),
                toPieceJointesResponse(dossierInscription.getPhotos())
        );
    }

    private List<PieceJointeResponse> toPieceJointesResponse(List<PieceJointe> carteIdentites) {
        return carteIdentites.stream()
                .map(pj -> new PieceJointeResponse(pj.getUrl()))
                .collect(toList());
    }

}

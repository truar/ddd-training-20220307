package com.zenika.training.ddd.pcscol;

import com.zenika.training.ddd.pcscol.application.VoeuApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PcscolParcoursapprenantApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VoeuApplicationService service;

    @Test
    void should_create_and_fetch_dossier_inscription() throws Exception {
        //language=JSON
        String dossierInscriptionRequest = "{\n" +
                "  \"codeAcces\": \"012345\",\n" +
                "  \"nomApprenant\": \"LEO\",\n" +
                "  \"prenomApprenant\": \"LEO\",\n" +
                "  \"dateDeNaissanceApprenant\": \"1989-12-07\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(post("/dossierInscriptions")
                        .content(dossierInscriptionRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String idDossier = mvcResult.getResponse().getContentAsString();

        mockMvc.perform(get("/dossierInscriptions/{id}", idDossier))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idDossier))
                .andExpect(jsonPath("$.nom").value("LEO"))
                .andExpect(jsonPath("$.prenom").value("LEO"))
                .andExpect(jsonPath("$.carteIdentites").isEmpty())
                .andExpect(jsonPath("$.photos").isEmpty());
//                .andExpect(jsonPath("$.dateDeNaissance").value("1989-12-07"));

        service.ajouterVoeu(idDossier, "FOR");

    }

    @Test
    void name() {


    }
}

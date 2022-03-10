package com.zenika.training.ddd.pcscol;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PcscolParcoursApprenantApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_create_and_fetch_registration_application() throws Exception {
        //language=JSON
        String registrationApplicationRequest = "{\n" +
                "  \"lastName\": \"LEO\",\n" +
                "  \"firstName\": \"LEO\",\n" +
                "  \"birthDate\": \"1989-12-07\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(post("/registrationApplications")
                        .content(registrationApplicationRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String idApplication = mvcResult.getResponse().getContentAsString();

        mockMvc.perform(get("/registrationApplications/{id}", idApplication))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idApplication))
                .andExpect(jsonPath("$.lastName").value("LEO"))
                .andExpect(jsonPath("$.firstName").value("LEO"))
                .andExpect(jsonPath("$.identityCard").isEmpty())
                .andExpect(jsonPath("$.photos").isEmpty());
    }
}

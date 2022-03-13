package com.zenika.training.ddd.pcscol.infrastructure.rest;

import com.zenika.training.ddd.pcscol.domain.CofGateway;
import org.springframework.web.client.RestTemplate;

public class HttpCofGateway implements CofGateway {

    private RestTemplate restTemplate;

//    @Override
//    public Training fetchByTrainingCode(String trainingCode) {
//        TraningDTO dto =  restTemplate.getForEntity(...);
//        return toTraining(dto);
//    }
}

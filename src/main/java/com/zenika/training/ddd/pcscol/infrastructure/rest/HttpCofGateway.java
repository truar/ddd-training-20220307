package com.zenika.training.ddd.pcscol.infrastructure.rest;

import com.zenika.training.ddd.pcscol.domain.CofGateway;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

public class HttpCofGateway implements CofGateway {

    private RestTemplate restTemplate;

//    @Override
//    public Formation fetchByCodeFormation(String codeFormation) {
//        FormationDTO dto =  restTemplate.getForEntity();
//        return toFormation(dto);
//    }
}

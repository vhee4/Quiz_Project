package com.QuizProject.QuizProject.service.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExternalApi {
    public List<Object> callExternalApi(){
//        String url = "https://countriesnow.space/api/v0.1/countries";
        String url = "https://opentdb.com/api.php?amount=20&category=18&difficulty=easy&type=multiple";
        RestTemplate restTemplate = new RestTemplate();
        Object[] objects = new Object[]{restTemplate.getForEntity(url, Object.class).getBody()} ;
        List<Object> objectList = Arrays.asList(objects);
        return objectList.stream().limit(10).collect(Collectors.toList());
    }
}

package com.example.FirstAPIJune24.Component;

import com.example.FirstAPIJune24.Dtos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtils {

    RestTemplate restTemplate;

    @Autowired
    public AuthUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String tokenValue){
        String body = "{\"token\":\"" + tokenValue + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

        try {
            Token token = this.restTemplate.postForObject("http://UserServiceMay24/users/validate-token", httpEntity, Token.class);
//            Token token = this.restTemplate.postForObject("http://user-service-qa.us-east-1.elasticbeanstalk.com/user/validate-token", httpEntity, Token.class);
            return token != null;
        } catch (Exception e){
            return false;
        }
    }
}

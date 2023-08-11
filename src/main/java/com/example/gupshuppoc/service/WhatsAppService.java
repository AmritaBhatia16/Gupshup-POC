package com.example.gupshuppoc.service;

import com.example.gupshuppoc.model.WhatsAppMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${gupshup.api.key}")
    private String apiKey;
    @Value("${gupshup.api.endpoint}")
    private String apiEndpoint;

    public String sendWhatsAppMessage(WhatsAppMessage message) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("apikey", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ObjectMapper mapper = new ObjectMapper();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        try {
            map.add("message", mapper.writeValueAsString(message.getMessage()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        map.add("src.name", message.getSrcName());
        map.add("channel", message.getChannel());
        map.add("source", message.getSource());
        map.add("destination", message.getDestination());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiEndpoint,
                HttpMethod.POST,
                entity, String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to send WhatsApp message: " + responseEntity.getStatusCode());
        }

    }


    public String optInUser(String appName, String user) {

        String endpoint = "https://api.gupshup.io/sm/api/v1/app/opt/in/" + appName;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("apikey", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("user", user);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                entity, String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to opt-in user: " + responseEntity.getStatusCode());
        }

    }

}

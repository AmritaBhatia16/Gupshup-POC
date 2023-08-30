package com.example.gupshuppoc.service;

import com.example.gupshuppoc.model.WhatsAppMessage;
import com.example.gupshuppoc.model.WhatsAppTemplateMessage;
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

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${gupshup.api.key}")
    private String apiKey;
    @Value("${gupshup.api.message.endpoint}")
    private String messageEndpoint;
    @Value("${gupshup.api.template.endpoint}")
    private String templateMessageEndpoint;
    @Value("${gupshup.app.name}")
    private String appName;
    @Value("${whatsapp.business.phone.number}")
    private String businessPhoneNumber;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();


    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("apikey", apiKey);
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }


    public String sendWhatsAppMessage(WhatsAppMessage message) {

        try {
            HttpHeaders headers = createHeaders();

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("message", objectMapper.writeValueAsString(message.message()));
            map.add("src.name", appName);
            map.add("channel", message.channel());
            map.add("source", businessPhoneNumber);
            map.add("destination", message.destination());

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    messageEndpoint,
                    HttpMethod.POST,
                    requestEntity, String.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Failed to send WhatsApp message: " + responseEntity.getStatusCode());
            }

        } catch (JsonProcessingException e) {
            throw new WhatsAppApiException("Failed to serialize WhatsApp message", e);
        } catch (RestClientException e) {
            throw new WhatsAppApiException("Failed to send WhatsApp message", e);
        }
    }


    public String sendTemplateMessage(WhatsAppTemplateMessage message) {

        try {
            HttpHeaders headers = createHeaders();

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("template", objectMapper.writeValueAsString(message.template()));
            map.add("message", objectMapper.writeValueAsString(message.message()));
            map.add("src.name", appName);
            map.add("source", businessPhoneNumber);
            map.add("destination", message.destination());

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    templateMessageEndpoint,
                    HttpMethod.POST,
                    requestEntity, String.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new WhatsAppApiException("Failed to send WhatsApp message: " + responseEntity.getStatusCode());
            }

        } catch (JsonProcessingException e) {
            throw new WhatsAppApiException("Failed to serialize WhatsApp template", e);
        } catch (RestClientException e) {
            throw new WhatsAppApiException("Failed to send WhatsApp template", e);
        }

    }


    public String optInUser(String user) {

        final String endpoint = "https://api.gupshup.io/sm/api/v1/app/opt/in/" + appName;

        HttpHeaders headers = createHeaders();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("user", user);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    requestEntity, String.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new WhatsAppApiException("Failed to opt-in user: " + responseEntity.getStatusCode());
            }
        } catch (RestClientException e) {
            throw new WhatsAppApiException("Failed to opt-in user", e);
        }
    }


    public String getTemplateList() {

        final String endpoint = "https://api.gupshup.io/sm/api/v1/template/list/" + appName;

        HttpHeaders headers = createHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                requestEntity, String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new WhatsAppApiException("Failed to retrieve template list: " + responseEntity.getStatusCode());
        }

    }


    public static class WhatsAppApiException extends RuntimeException {
        public WhatsAppApiException(String message) {
            super(message);
        }

        public WhatsAppApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}

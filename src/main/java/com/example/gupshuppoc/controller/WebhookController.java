package com.example.gupshuppoc.controller;

import com.example.gupshuppoc.model.WebhookPayload;
import com.example.gupshuppoc.model.WhatsAppMessageResponse;
import com.example.gupshuppoc.service.WhatsAppService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebhookController {
    @PostMapping("/webhook")
    public ResponseEntity<String> handleInboundEvent(@RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            final WebhookPayload parsedPayload = objectMapper.readValue(
                    payload,
                    WebhookPayload.class
            );
            System.out.println(parsedPayload);
            return ResponseEntity.ok("Webhook payload received and processed.");

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize inbound payload", e);
        }
    }
}

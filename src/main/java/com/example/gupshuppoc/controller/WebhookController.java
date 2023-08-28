package com.example.gupshuppoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebhookController {
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhookEvent(@RequestBody String payload) {
        System.out.println(payload);
        return ResponseEntity.ok("Webhook payload received and processed.");
    }
}

package com.example.gupshuppoc.model;

public record WhatsAppTemplateMessage(
        String destination,
        Object template,
        Object message
) {}
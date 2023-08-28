package com.example.gupshuppoc.model;

public record WhatsAppTemplateMessage(
        String srcName,
        String source,
        String destination,
        Object template,
        Object message
) {}
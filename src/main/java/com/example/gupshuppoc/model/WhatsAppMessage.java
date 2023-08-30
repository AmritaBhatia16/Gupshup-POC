package com.example.gupshuppoc.model;

public record WhatsAppMessage(
        String channel,
        String destination,
        Object message
//        Boolean disablePreview,
//        Boolean encode
) {}

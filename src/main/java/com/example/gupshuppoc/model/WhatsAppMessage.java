package com.example.gupshuppoc.model;

public record WhatsAppMessage(
        String srcName,
        String channel,
        String source,
        String destination,
        Object message
//        Boolean disablePreview,
//        Boolean encode
) {}

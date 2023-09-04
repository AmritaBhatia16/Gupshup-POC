package com.example.gupshuppoc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WhatsAppMessageResponse (
    @JsonProperty("status") String status,
    @JsonProperty("messageId") String messageId
) {}

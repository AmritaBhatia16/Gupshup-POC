package com.example.gupshuppoc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebhookPayload (
        @JsonProperty("app") String app,
        @JsonProperty("timestamp") Long timestamp,
        @JsonProperty("version") Integer version,
        @JsonProperty("type") String type,
        @JsonProperty("payload") Object payload
) {}

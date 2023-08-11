package com.example.gupshuppoc.model;

import lombok.Data;

@Data
public class WhatsAppMessage {
    private String channel;
    private String source;
    private String srcName;
    private String destination;
    private Object message;
    /*
    private Boolean disablePreview;
    private Boolean encode;
     */

}

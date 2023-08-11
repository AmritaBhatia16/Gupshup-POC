package com.example.gupshuppoc.controller;

import com.example.gupshuppoc.model.WhatsAppMessage;
import com.example.gupshuppoc.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping(path="/send")
    public @ResponseBody String sendWhatsApp(@RequestBody WhatsAppMessage message) {
        return whatsAppService.sendWhatsAppMessage(message);
    }

    @PostMapping(path="opt-in")
    public @ResponseBody String optInUser(@RequestParam String appName, @RequestParam String user) {
        return whatsAppService.optInUser(appName, user);
    }

}

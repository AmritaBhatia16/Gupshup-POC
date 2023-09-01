package com.example.gupshuppoc.controller;

import com.example.gupshuppoc.model.WhatsAppMessageResponse;
import com.example.gupshuppoc.model.WhatsAppMessage;
import com.example.gupshuppoc.model.WhatsAppTemplateMessage;
import com.example.gupshuppoc.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public @ResponseBody WhatsAppMessageResponse sendWhatsApp(@RequestBody WhatsAppMessage message) {
        return whatsAppService.sendWhatsAppMessage(message);
    }

    @PostMapping(path="/send/template")
    public @ResponseBody WhatsAppMessageResponse sendTemplate(@RequestBody WhatsAppTemplateMessage message) {
        return whatsAppService.sendTemplateMessage(message);
    }

    @PostMapping(path="/opt-in")
    public @ResponseBody String optInUser(@RequestParam String user) {
        return whatsAppService.optInUser(user);
    }

    @GetMapping(path="/template/list")
    public @ResponseBody String getTemplateList() {
        return whatsAppService.getTemplateList();
    }

}

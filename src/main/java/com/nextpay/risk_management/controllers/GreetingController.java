package com.nextpay.risk_management.controllers;

import com.google.common.base.Optional;
import com.nextpay.risk_management.model.Greeting;
import com.nextpay.risk_management.model.HelloMessage;
import com.nextpay.risk_management.model.RiskTransaction;
import com.nextpay.risk_management.repository.RiskTransRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
@RestController
class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RiskTransRepo riskTransRepo;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(@RequestBody HelloMessage message) throws Exception {

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    public void fireGreeting() {
        System.out.println("Fire");
        this.template.convertAndSend("/topic/greetings", new Greeting("Fire"));
    }

    @GetMapping("/test")
    public String ads(){
        fireGreeting();
        return "success";
    }

    public void riskTrans() {
        Optional<RiskTransaction> risk = riskTransRepo.findByServiceCode("");
        this.template.convertAndSend("/topic/greetings", risk);
    }

    @GetMapping("/risk")
    public String risk(){
        fireGreeting();
        return "success";
    }

}